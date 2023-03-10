USE employees;

CREATE Table api_key_table (
                               user_name VARCHAR(255) NOT NULL,
                               user_level ENUM("BASIC", "UPDATE", "ADMIN") NOT NULL,
                               api_key VARCHAR(255) NOT NULL,
                               primary key(user_name),
                               UNIQUE(user_name, api_key)
);