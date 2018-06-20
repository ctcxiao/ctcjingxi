CREATE TABLE Users(
    id INT PRIMARY KEY auto_increment,
    name varchar(255)
) engine=InnoDB DEFAULT CHARSET = gbk;

CREATE TABLE Orders(
    id INT auto_increment,
    userId INT,
    status VARCHAR(255) NOT NULL,
    buyCount INT NOT NULL,
    buyTime VARCHAR(255),
    totalPrice DOUBLE(20,2) NOT NULL,
    orderDetail varchar(255) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(userId) REFERENCES Users(id)
) engine=InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE Products(
    id INT auto_increment,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    price DOUBLE(10,2) NOT NULL,
    userId VARCHAR(20),
    PRIMARY KEY(id)
) engine=InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE LogisticsRecords(
    id INT auto_increment,
    totalPrice DOUBLE(20,2) NOT NULL,
    userId INT,
    createTime VARCHAR(255) NOT NULL,
    logisticsStatus VARCHAR(255),
    purchaseString VARCHAR(255),
    PRIMARY KEY(id)
) engine=InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE Inventory(
    id INT auto_increment,
    counts INT,
    lockCount INT,
    productId INT,
    PRIMARY KEY(id)
)