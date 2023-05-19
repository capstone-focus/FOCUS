create table book (
       book_id bigint not null auto_increment,
        author varchar(255) not null,
        description text not null,
        image_url varchar(255),
        title varchar(255) not null unique,
        primary key (book_id)
    ) engine=InnoDB;

    create table chapter (
       book_id bigint not null,
        chapter_seq integer not null,
        description text not null,
        primary key (book_id, chapter_seq),
        foreign key(book_id) references book(book_id)
    ) engine=InnoDB;

    create table genre (
       genre_id integer not null auto_increment,
        name varchar(255) not null unique,
        primary key (genre_id)
    ) engine=InnoDB;

    create table member (
       member_id bigint not null auto_increment,
        email varchar(255) not null unique,
        name varchar(255) not null,
        primary key (member_id)
    ) engine=InnoDB;

    create table member_genre (
       member_genre_id bigint not null auto_increment,
        genre_id integer not null,
        member_id bigint not null,
        primary key (member_genre_id),
        foreign key(member_id) references member(member_id),
        foreign key(genre_id) references genre(genre_id)
    ) engine=InnoDB;