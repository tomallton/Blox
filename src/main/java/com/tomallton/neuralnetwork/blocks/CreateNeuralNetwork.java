package com.tomallton.neuralnetwork.blocks;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tomallton.blox.Block;
import com.tomallton.blox.Script;
import com.tomallton.neuralnetwork.ActivationFunction;
import com.tomallton.neuralnetwork.util.FileUtils;
import com.tomallton.neuralnetwork.util.MathUtils;
import com.tomallton.neuralnetwork.util.Pair;
import com.tomallton.neuralnetwork.util.StringUtils;

public class CreateNeuralNetwork implements Block {
    private static final double DEFAULT_TRAIN_PROPORTION = 0.7;

    private final String name;
    private final NeuralNetwork model;

    public CreateNeuralNetwork(String name, String file, int featureColumnStart, int featureColumnEnd, int labelColumn) {
        this(name, file, featureColumnStart, featureColumnEnd, labelColumn, DEFAULT_TRAIN_PROPORTION);
    }

    public CreateNeuralNetwork(String name, String file, int featureColumnStart, int featureColumnEnd, int labelColumn, double trainProportion) {
        this(name, file, featureColumnStart, featureColumnEnd, labelColumn, labelColumn + 1, trainProportion);
    }

    public CreateNeuralNetwork(String name, String file, int featureColumnStart, int featureColumnEnd, int labelColumnStart, int labelColumnEnd, double trainProportion) {
        File f = new File(file);

        if (!f.exists() || !f.isFile()) {
            throw new IllegalArgumentException("File '" + file + "' does not exist");
        }

        List<String> lines = FileUtils.readFile(file);

        List<List<String>> labels = new ArrayList<>();
        for (int j = labelColumnStart; j < labelColumnEnd; j++) {
            labels.add(new ArrayList<>());
        }

        double[][] X = new double[lines.size()][];
        double[][] y = new double[lines.size()][1];

        for (int i = 0; i < lines.size(); i++) {
            String[] fields = lines.get(i).split(",");

            // convert label columns to one-hot if they are not numbers
            for (int j = labelColumnStart; j < labelColumnEnd; j++) {
                String label = fields[j];
                try {
                    Double.parseDouble(label);
                } catch (Exception exception) {
                    List<String> currentLabels = labels.get(j - labelColumnStart);

                    // field not a number, convert to number
                    if (!currentLabels.contains(label)) {
                        currentLabels.add(label);
                    }

                    fields[j] = String.valueOf(currentLabels.indexOf(label));
                }

            }

            X[i] = Arrays.stream(Arrays.copyOfRange(fields, featureColumnStart, featureColumnEnd)).mapToDouble(Double::valueOf).toArray();
            y[i] = Arrays.stream(Arrays.copyOfRange(fields, labelColumnStart, labelColumnEnd)).mapToDouble(Double::valueOf).toArray();
        }

        // normalize data
        MathUtils.normalize(X);

        NeuralNetwork model = new NeuralNetwork(new Layer(X[0].length, ActivationFunction.SIGMOID), new Layer(X[0].length / 2, ActivationFunction.SIGMOID), new Layer(1));

        Pair<Pair<double[][], double[][]>, Pair<double[][], double[][]>> trainTestSplit = MathUtils.trainTestSplit(X, y, 1 - trainProportion);

        double[][] xTrain = trainTestSplit.getLeft().getLeft();
        double[][] yTrain = trainTestSplit.getLeft().getRight();
        double[][] xTest = trainTestSplit.getRight().getLeft();
        double[][] yTest = trainTestSplit.getRight().getRight();

        model.train(xTrain, yTrain, 0.1, 100);

        double[][] yPredict = model.predict(xTest);

        // round prediction to nearest whole value
        MathUtils.apply(yPredict, a -> a >= 0.5 ? 1D : 0D);

        // print accuracy
        double accuracy = MathUtils.accuracy(yPredict, yTest);
        System.out.println("Trained neural network model, accuracy: " + StringUtils.formatPercentage(accuracy));

        this.name = name;
        this.model = model;
    }

    @Override
    public void onLoad(Script<?> script) {
        script.setAttribute(name, model);
    }
}