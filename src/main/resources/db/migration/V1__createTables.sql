CREATE TABLE IF NOT EXISTS TELEGRAM_USERS (chatId INT8 PRIMARY KEY, platform_list TEXT, type_list TEXT);

CREATE TABLE IF NOT EXISTS GIVEAWAYS (id VARCHAR(255) PRIMARY KEY, title TEXT, worth VARCHAR(255),
    thumbnail VARCHAR(255), image VARCHAR(255), description TEXT, instructions TEXT,
    open_giveaway_url VARCHAR(255), published_date VARCHAR(255), end_date VARCHAR(255),
    users VARCHAR(255), status VARCHAR(255), gamerpower_url VARCHAR(255),
    open_giveaway VARCHAR(255), "type" VARCHAR(255), platforms VARCHAR(255));