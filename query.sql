use poll_db;

CREATE TABLE Users (
    UserId  int,
    UserName varchar(255),
    Email varchar(255),
    UserPassword varchar(255),
    Pin int,
    PRIMARY KEY(UserId)
  
);
CREATE TABLE HasPoll (
    PollId varchar(255),
    UserId varchar(255),
	FOREIGN KEY(UserId) REFERENCES Users(Users),
	FOREIGN KEY(UserId) REFERENCES Poll(PollId),
    CONSTRAINT HasPoll_Primary PRIMARY KEY (PollId,UserId)
);

CREATE TABLE Poll (
	Pin int,
    PollName varchar(255),
    Question varchar(255),
    Choices varchar(255),
    PollId varchar(255),
    CONSTRAINT Poll_Primary PRIMARY KEY (PollId)
);

CREATE TABLE Vote (
	Pin int,
    PollId varchar(255),
	FOREIGN KEY(PollId) REFERENCES Poll(PollId),
    CONSTRAINT Vote_Primary PRIMARY KEY (PollId,Pin)
);