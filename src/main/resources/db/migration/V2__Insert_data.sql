insert into users (id, name) values (1, 'ctc');

INSERT INTO Products (name, description, price) VALUES ('电脑', '坚如磐石', 5999);
INSERT INTO Products (name, description, price) VALUES ('方便面', '很好吃，康师傅', 4.8);

INSERT INTO Inventory (productId, counts, lockCount) VALUES (1, 4, 0);
INSERT INTO Inventory (productId, counts, lockCount) VALUES (2, 4, 0);