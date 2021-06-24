# 2020秋机器学习理论与方法实验
运行方法：\
项目文件中已经包含了数据集，且只依赖Java核心类库，因此直接利用maven导入项目之后运行Main文件中的main方法即可完成训练过程。

输出实例(iter代表训练轮数，RMSE loss代表训练误差)：

iter = 0, RMSE loss = 0.62419385

iter = 1, RMSE loss = 0.4733614


# 2020 HIT Fall Machine Learning Theory and Method Experiment
Start Up:\
The project file already contains the dataset, and only relies on the Java core library, so directly use maven to import the project and run the main method in the Main.java file to complete the training process.

Output example (iter represents the number of training rounds, RMSE loss represents the training error):

iter = 0, RMSE loss = 0.62419385

iter = 1, RMSE loss = 0.4733614


## 神经网络程序的实现

## 1. 数据集简介


本次实验选用的是Boston房价数据集。这是一个回归任务，包含 13 个关于房屋具体情
况的属性，如附近的犯罪率，居民年龄，是否临河等等，输出结果为房价。
数据集的各行之间数值差距比较大，因此在使用前先对数据集的各行进行归一化，这样
可以减小量纲的影响，归一化后的数据集附在实验代码中。

## 2. 实验环境

实验在windows系统下进行，Java Jdk 11
实验代码已上传至github: https://github.com/dxzmpk/ml_experment.git。

## 3. 算法介绍

##### 神经网络已经广泛应用在工业实践中，它是通过将多个非线性神经单元首尾相连组合成的。

##### 考虑到实验的复杂性，选择单层线性网络进行实现。网络主要结构如下图：

在更新时采用的方法为梯度下降法。梯度下降法（gradient descent）是求解无约束最优化问
题最常用的方法，它是一种迭代方法，每一步主要的操作是求解目标函数的梯度向量，将当
前位置的负梯度方向作为搜索方向。


## 4. 程序结构
![image](https://user-images.githubusercontent.com/34058412/123198767-3ca2d800-d4e0-11eb-8cf8-288842bfbabe.png)
依据依赖反转原则，将数据加载器和模型分别抽象出接口，分别进行实现，Main类中包含
程序的主入口，执行main函数将启动程序的训练并输出训练的结果。
![image](https://user-images.githubusercontent.com/34058412/123198874-6e1ba380-d4e0-11eb-9da0-f11ec94d8a05.png)


## 5. 训练过程

### 5. 1 训练方法

训练过程在BostonRegressionModel的train方法中实现。预先设定的训练轮数为 1000 。
deltaW为和数据维数一样的列表，用来保存每次更新w的变化幅度。其计算方法为：

在计算输出值wx时，使用矩阵和向量的乘法，其实现在matrix_vector_multiply方法中。

### 5.2 早停

实验中选用RMSE loss来衡量训练损失，其数值计算方法在rmseLoss函数中，初始设定轮
数为 1000 ，但是实验中发现在 300 之后训练损失下降极为缓慢，因此引入早停机制。即如
果连续 10 次训练损失下降小于0.001，就提前结束训练。

### 5.3 训练结果

开始训练时将所有w都设为 0 ，此时损失最大，随着训练的进行损失逐渐减小：
![image](https://user-images.githubusercontent.com/34058412/123198898-78d63880-d4e0-11eb-837f-8098915efb24.png)

##### 最终训练到 308 轮时，触发早停机制，训练损失为：1.8815


## 附录

### 预处理代码

import pandas as pd
from sklearn import preprocessing

// 读入数据， 转换类型
df = pd.read_csv(r"D:\archive\housing.csv", header = None)
values = []
for i in df.values:
values.append([float(c) for c in i[ 0 ].strip().split()])
// min_max规范化
min_max_scaler = preprocessing.MinMaxScaler()
x_scaled = min_max_scaler.fit_transform(values)
df = pd.DataFrame(x_scaled)
