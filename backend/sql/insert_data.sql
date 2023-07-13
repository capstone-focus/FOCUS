insert into genre (name)
values
('pop'),
('k-pop'),
('r-n-b'),
('hip-hop'),
('country'),
('acoustic'),
('blues'),
('jazz'),
('classical'),
('rock'),
('none');

insert into book(title, author, description)
values ('1776', 'David McCullough', '1776 tells the story of an early and crucial year in the American Revolution, whose outcome made possible the ultimate victory of the American side seven years later. The book focuses principally on the Continental Army, specifically those men under the direct command of General George Washington during the first 15 months of the American Revolutionary War, with a particular emphasis on the titular year. By telling the story of the soldiers rather than the signers of the Declaration of Independence in Philadelphia, 1776 makes clear how precarious and uncertain the situation was even as the momentous words of the Declaration of Independence were being composed. Against the longest odds imaginable, Washington managed to expel the British from Boston and then, despite a series of misjudgments and setbacks, was able to preserve his ragtag army in the face of superior opposition until the end of the campaigning season in early January 1777.');

insert into chapter (chapter_seq, book_id) values (1, 1);
insert into chapter (chapter_seq, book_id) values (2, 1);
insert into chapter (chapter_seq, book_id) values (3, 1);
insert into chapter (chapter_seq, book_id) values (4, 1);
insert into chapter (chapter_seq, book_id) values (5, 1);
insert into chapter (chapter_seq, book_id) values (6, 1);
insert into chapter (chapter_seq, book_id) values (7, 1);
