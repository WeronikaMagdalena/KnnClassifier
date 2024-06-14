# KNN Classifier

This Java program implements a K-Nearest Neighbors (KNN) classifier for classification tasks. The classifier is designed to work with a normalized dataset and provides methods for classification and accuracy calculation. The program includes randomization to ensure robust training and test splits.

## Table of Contents
1. [Overview](#overview)
2. [Usage](#usage)
3. [Methods](#methods)
4. [Screenshots](#screenshots)
5. [Comments and Explanations](#comments-and-explanations)

## Overview
The KNN Classifier is a simple yet powerful tool for classification tasks. It works by identifying the k-nearest neighbors to a given instance and assigning the most common class among these neighbors to the instance. This implementation includes:
- Randomized splitting of the dataset into training and test sets.
- Distance calculation using Euclidean distance.
- Accuracy calculation to evaluate the classifier's performance.

## Usage
1. **Initialize the Dataset**:
   - Load your dataset into an instance of the `Dataset` class.
   - Normalize the dataset as needed.

2. **Create a KNN Classifier**:
   ```java
   Dataset normalizedData = // your normalized dataset;
   double trainingDatasetSize = 0.8; // 80% training data, 20% test data
   int k = 5; // number of nearest neighbors

   KnnClassifier classifier = new KnnClassifier(normalizedData, trainingDatasetSize, k);
