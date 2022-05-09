##Set working directory

setwd("./")

##Load the the packages required to predict

library(caret)
library(e1071)

##Load de predictor model and the featured vector

load("model.Rda")

microserviciosTest <- read.csv("FeatureVector.csv")

##Predict the results

microserviciosPredictionC50 <- predict(DTFit, microserviciosTest)

##Save the results

write.table(microserviciosPredictionC50, file="microservicePrediction.txt", row.names = FALSE, col.names = FALSE)
