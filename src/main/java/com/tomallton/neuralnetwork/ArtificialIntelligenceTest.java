package com.tomallton.neuralnetwork;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tomallton.blox.Blox;
import com.tomallton.neuralnetwork.blocks.CreateNeuralNetwork;
import com.tomallton.neuralnetwork.blocks.Layer;
import com.tomallton.neuralnetwork.blocks.NeuralNetwork;
import com.tomallton.neuralnetwork.blocks.Predict;
import com.tomallton.neuralnetwork.blocks.Train;
import com.tomallton.neuralnetwork.util.FileUtils;
import com.tomallton.neuralnetwork.util.MathUtils;
import com.tomallton.neuralnetwork.util.Pair;
import com.tomallton.neuralnetwork.util.StringUtils;

public class ArtificialIntelligenceTest {

    public static void main(String[] args) {
        createTest();
    }

    public static void createTest() {
        Blox<?> blox = new Blox<>(false);

        // add blocks
        blox.addBlock(Layer.class);
        blox.addBlock(NeuralNetwork.class);
        blox.addBlock(CreateNeuralNetwork.class);
        blox.addBlock(Predict.class);
        blox.addBlock(Train.class);

        blox.load(new File("src/main/resources"));
    }

    public static void cancerClassificationTest() {
        List<String> lines = FileUtils.readFile("src/main/resources/cancer.csv");
        List<String> labels = new ArrayList<>();

        double[][] X = new double[lines.size()][];
        double[][] y = new double[lines.size()][1];

        for (int i = 0; i < lines.size(); i++) {
            String[] fields = lines.get(i).split(",");

            // skip id and classification field
            X[i] = Arrays.stream(Arrays.copyOfRange(fields, 2, fields.length)).mapToDouble(Double::valueOf).toArray();
            // label as 1 if positive for cancer (malignant)
            String label = fields[1];
            if (!labels.contains(label)) {
                labels.add(label);
            }
            y[i] = new double[] { fields[1].equals("M") ? 1 : 0 };
        }

        // normalize data
        MathUtils.normalize(X);

        NeuralNetwork model = new NeuralNetwork(new Layer(X[0].length, ActivationFunction.SIGMOID), new Layer(15, ActivationFunction.SIGMOID), new Layer(1));

        Pair<Pair<double[][], double[][]>, Pair<double[][], double[][]>> trainTestSplit = MathUtils.trainTestSplit(X, y);

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
        System.out.println("Accuracy: " + StringUtils.formatPercentage(accuracy));
    }

    public static void calculationsTest() {

        // https://mattmazur.com/2015/03/17/a-step-by-step-backpropagation-example/
        NeuralNetwork model = new NeuralNetwork(new Layer(new double[][] { { 0.15, 0.25 }, { 0.2, 0.3 }, { 0.35, 0.35 } }, ActivationFunction.SIGMOID),
                new Layer(new double[][] { { 0.4, 0.5 }, { 0.45, 0.55 }, { 0.6, 0.6 } }, ActivationFunction.SIGMOID));

        model.train(new double[][] { { 0.05, 0.1 } }, new double[][] { { 0.01, 0.99 } }, 0.5);

        System.out.println(model);
    }
}