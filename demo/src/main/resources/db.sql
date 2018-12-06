CREATE TABLE `userinfo` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` char(16) COLLATE utf8_unicode_ci NOT NULL,
  `Password` char(128) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2014 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE `userSign` (
  `Id` int(11) NOT NULL,
  `UserId` int(11) NOT NULL,
  `PersonalSign` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `userId` (`UserId`),
  CONSTRAINT `userId` FOREIGN KEY (`UserId`) REFERENCES `userinfo` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


