insert into book(title) values ('a');
insert into book(title) values ('b');

insert into chapter(chapter_seq, book_id) values (1, 1);
insert into chapter(chapter_seq, book_id) values (2, 1);
insert into chapter(chapter_seq, book_id) values (3, 1);

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