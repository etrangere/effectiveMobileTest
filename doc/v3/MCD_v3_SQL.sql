#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: user
#------------------------------------------------------------

CREATE TABLE user(
        id        Int  Auto_increment  NOT NULL ,
        username  Varchar (100) NOT NULL ,
        password  Varchar (100) NOT NULL ,
        firstName Varchar (100) NOT NULL ,
        lastName  Varchar (100) NOT NULL ,
        email     Varchar (100) NOT NULL ,
        executor  Bool NOT NULL
	,CONSTRAINT user_PK PRIMARY KEY (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: task
#------------------------------------------------------------

CREATE TABLE task(
        id          Int  Auto_increment  NOT NULL ,
        header      Text NOT NULL ,
        description Longtext NOT NULL ,
        status      Varchar (100) NOT NULL ,
        priority    Varchar (100) NOT NULL ,
        author      Varchar (100) NOT NULL ,
        executor    Varchar (100) NOT NULL ,
        id_user     Int NOT NULL
	,CONSTRAINT task_PK PRIMARY KEY (id)

	,CONSTRAINT task_user_FK FOREIGN KEY (id_user) REFERENCES user(id)
	,CONSTRAINT task_user_AK UNIQUE (id_user)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: comments
#------------------------------------------------------------

CREATE TABLE comments(
        id      Int  Auto_increment  NOT NULL ,
        message Longtext NOT NULL ,
        id_task Int NOT NULL
	,CONSTRAINT comments_PK PRIMARY KEY (id)

	,CONSTRAINT comments_task_FK FOREIGN KEY (id_task) REFERENCES task(id)
)ENGINE=InnoDB;

