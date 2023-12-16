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
        executor    Varchar (100) NOT NULL
	,CONSTRAINT task_PK PRIMARY KEY (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: comments
#------------------------------------------------------------

CREATE TABLE comments(
        id      Int  Auto_increment  NOT NULL ,
        message Longtext NOT NULL ,
        id_task Int NOT NULL ,
        id_user Int NOT NULL
	,CONSTRAINT comments_PK PRIMARY KEY (id)

	,CONSTRAINT comments_task_FK FOREIGN KEY (id_task) REFERENCES task(id)
	,CONSTRAINT comments_user0_FK FOREIGN KEY (id_user) REFERENCES user(id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: user_task
#------------------------------------------------------------

CREATE TABLE user_task(
        id      Int NOT NULL ,
        id_user Int NOT NULL
	,CONSTRAINT user_task_PK PRIMARY KEY (id,id_user)

	,CONSTRAINT user_task_task_FK FOREIGN KEY (id) REFERENCES task(id)
	,CONSTRAINT user_task_user0_FK FOREIGN KEY (id_user) REFERENCES user(id)
)ENGINE=InnoDB;

