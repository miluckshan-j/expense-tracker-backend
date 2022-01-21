-- ALTER DATABASE test CHARACTER SET = 'utf8mb4' COLLATE = 'utf8mb4_unicode_ci';
-- ALTER TABLE category CONVERT TO CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci';

INSERT INTO category (id, name, logo, budget, date) VALUES (1, 'General', '\uD83C\uDFF7️', 0.0, '2020-01-25');
INSERT INTO category (id, name, logo, budget, date) VALUES (2, 'Gift', '\uD83C\uDF81', 0.0, '2020-01-25');
INSERT INTO category (id, name, logo, budget, date) VALUES (3, 'Shopping', '\uD83D\uDED2', 0.0, '2020-01-25');

INSERT INTO transaction (id, note, amount, type, is_recurring, date, category_id) VALUES (4, null, 1500.0, 'income', false, '2020-01-25', 1);
INSERT INTO transaction (id, note, amount, type, is_recurring, date, category_id) VALUES (5, 'Bread', 5.0, 'expense', false, '2020-01-25', 2);
INSERT INTO transaction (id, note, amount, type, is_recurring, date, category_id) VALUES (6, 'Ice Coffee', 2.5, 'expense', false, '2020-01-25', 1);
INSERT INTO transaction (id, note, amount, type, is_recurring, date, category_id) VALUES (7, 'Birthday', 500.0, 'income', false, '2020-01-25', 3);
INSERT INTO transaction (id, note, amount, type, is_recurring, date, category_id) VALUES (8, null, 150.0, 'income', false, '2020-01-25', 1);
INSERT INTO transaction (id, note, amount, type, is_recurring, date, category_id) VALUES (9, 'Milk', 5.0, 'expense', false, '2020-01-25', 1);
INSERT INTO transaction (id, note, amount, type, is_recurring, date, category_id) VALUES (10, 'Mom Birthday', 2000.0, 'expense', false, '2020-01-25', 2);
INSERT INTO transaction (id, note, amount, type, is_recurring, date, category_id) VALUES (12, 'Groceries', 350.0, 'expense', false, '2020-01-25', 1);
