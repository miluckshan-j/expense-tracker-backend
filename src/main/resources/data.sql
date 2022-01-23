-- ALTER DATABASE test CHARACTER SET = 'utf8mb4' COLLATE = 'utf8mb4_unicode_ci';
-- ALTER TABLE category CONVERT TO CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci';

INSERT INTO category (id, name, budget, date) VALUES (991, 'General', 0.0, '2020-01-25');
INSERT INTO category (id, name, budget, date) VALUES (992, 'Gift', 0.0, '2020-01-25');
INSERT INTO category (id, name, budget, date) VALUES (993, 'Shopping', 0.0, '2020-01-25');

INSERT INTO transaction (id, note, amount, type, is_recurring, date, category_id) VALUES (991, null, 1500.0, 'income', false, '2020-02-04', 991);
INSERT INTO transaction (id, note, amount, type, is_recurring, date, category_id) VALUES (992, 'Bread', 5.0, 'expense', false, '2021-01-25', 992);
INSERT INTO transaction (id, note, amount, type, is_recurring, date, category_id) VALUES (993, 'Ice Coffee', 2.5, 'expense', false, '2020-01-25', 991);
INSERT INTO transaction (id, note, amount, type, is_recurring, date, category_id) VALUES (994, 'Birthday', 500.0, 'income', false, '2019-05-25', 993);
INSERT INTO transaction (id, note, amount, type, is_recurring, date, category_id) VALUES (995, null, 150.0, 'income', false, '2020-01-25', 991);
INSERT INTO transaction (id, note, amount, type, is_recurring, date, category_id) VALUES (996, 'Milk', 5.0, 'expense', false, '2021-04-15', 991);
INSERT INTO transaction (id, note, amount, type, is_recurring, date, category_id) VALUES (997, 'Mom Birthday', 2000.0, 'expense', false, '2021-01-25', 992);
INSERT INTO transaction (id, note, amount, type, is_recurring, date, category_id) VALUES (998, 'Groceries', 350.0, 'expense', false, '2021-10-25', 991);
