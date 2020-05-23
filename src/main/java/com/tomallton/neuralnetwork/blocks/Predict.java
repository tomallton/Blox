package com.tomallton.neuralnetwork.blocks;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.tomallton.blox.Block;
import com.tomallton.blox.Script;
import com.tomallton.neuralnetwork.util.FileUtils;
import com.tomallton.neuralnetwork.util.MathUtils;
import com.tomallton.neuralnetwork.util.StringUtils;

public class Predict implements Block {
    private final String modelName, file;
    private final int featureColumnStart, featureColumnEnd;

    public Predict(String modelName, String file, int featureColumnStart, int featureColumnEnd) {
        this.modelName = modelName;
        this.file = file;
        this.featureColumnStart = featureColumnStart;
        this.featureColumnEnd = featureColumnEnd;
    }

    @Override
    public void onLoad(Script<?> script) {
        if (!(script.getAttribute(modelName) instanceof NeuralNetwork)) {
            return;
        }
        NeuralNetwork model = (NeuralNetwork) script.getAttribute(modelName);

        File f = new File(file);

        if (!f.exists() || !f.isFile()) {
            throw new IllegalArgumentException("File '" + file + "' does not exist");
        }
        
        List<String> lines = FileUtils.readFile(file);

        double[][] X = new double[lines.size()][];

        for (int i = 0; i < lines.size(); i++) {
            String[] fields = lines.get(i).split(",");
            X[i] = Arrays.stream(Arrays.copyOfRange(fields, featureColumnStart, featureColumnEnd)).mapToDouble(Double::valueOf).toArray();
        }

        MathUtils.normalize(X);
        
        System.out.println(StringUtils.toString(model.predict(X)));
    }
    
}