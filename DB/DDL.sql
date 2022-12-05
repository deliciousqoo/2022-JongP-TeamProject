DROP TABLE member CASCADE CONSTRAINT;
DROP TABLE event CASCADE CONSTRAINT;
DROP TABLE vote CASCADE CONSTRAINT;
DROP TABLE participate CASCADE CONSTRAINT;
DROP TABLE attend CASCADE CONSTRAINT;
DROP TABLE progress CASCADE CONSTRAINT;

create table member(
    SSN char(5) not null,
    Name varchar(20) not null,
    Rank varchar(20),
    primary key (SSN)
);

create table event(
    EventNo int not null,
    EName varchar(50),
    ELocation varchar(50),
    EDate date,
    EDescription varchar(200),
    EStatus int not null,
    AttendCode varchar(4),
    AttendQR varchar(200),
    HostSSN char(5),
    foreign key (HostSSN) references member(ssn),
    primary key (EventNo)
);

create table attend(
    SSN char(5) not null,
    EventNo int not null,
    foreign key (SSN) references member(ssn),
    foreign key (EventNo) references event(EventNo),
    primary key (SSN, EventNo)
);

create table progress(
    EventNo int not null,
    ProgNo int not null,
    pContent varchar(200),
    foreign key (EventNo) references event(EventNo),
    primary key (EventNo, ProgNo)
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

create table participate(
    SSN char(5) not null,
    EventNo int not null,
    VoteNo int not null,
    Answer int not null,
    foreign key (SSN) references member(ssn),
    foreign key (EventNo, voteno) references vote(eventno, voteno),
    primary key (ssn, EventNo, VoteNo)
);


commit;

