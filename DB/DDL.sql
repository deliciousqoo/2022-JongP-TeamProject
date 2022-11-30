-- DROP TABLE member CASCADE CONSTRAINT;
-- DROP TABLE event CASCADE CONSTRAINT;
-- DROP TABLE vote CASCADE CONSTRAINT;
-- DROP TABLE options CASCADE CONSTRAINT;
-- DROP TABLE participate CASCADE CONSTRAINT;

create table member(
    SSN char(5) not null,
    Name varchar(20) not null,
    Rank varchar(20),
    primary key (SSN)
);

create table event(
    eventNo int not null,
    primary key(eventNo)
);

create table vote(
    EventNo int not null,
    VoteNo int not null,
    Agenda varchar(50),
    Content varchar(100),
    StartTime date,
    FinishTime date,
    foreign key (EventNo) references event(eventno),
    primary key (EventNo, VoteNo)
);

create table options(
    EventNo int not null,
    VoteNo int not null,
    optionNo int not null,
    VoteOption varchar(50) not null,
    foreign key (EventNo, voteno) references vote(eventno, voteno),
    primary key (EventNo, VoteNo, OptionNo)
);

create table participate(
    SSN char(5) not null,
    EventNo int not null,
    VoteNo int not null,
    Answer int not null,
    foreign key (SSN) references member(ssn),
    foreign key (EventNo, voteno, answer) references options(eventno, voteno, optionno),
    primary key (ssn, EventNo, VoteNo, Answer)
);
