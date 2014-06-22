
DROP TABLE IF EXISTS users;
CREATE TABLE users (
	id SERIAL PRIMARY KEY NOT NULL,
	user_name VARCHAR(16) UNIQUE NOT NULL,
	full_name VARCHAR(64) NOT NULL,
	email     VARCHAR(64) UNIQUE NOT NULL,
	password  VARCHAR(64) NOT NULL
);

DROP TABLE IF EXISTS posts;
CREATE TABLE posts (
	id SERIAL PRIMARY KEY NOT NULL,
	title VARCHAR(200) NOT NULL,
	markdown TEXT,
	rendered TEXT,
	slug VARCHAR(64) UNIQUE NOT NULL
);
DROP INDEX IF EXISTS slug_index;
CREATE INDEX slug_index ON posts (slug);
-- TODO Create GIN indicies for title & markdown (full text search)

DROP TABLE IF EXISTS tags;
CREATE TABLE tags(
	id SERIAL PRIMARY KEY NOT NULL,
	name VARCHAR(32) UNIQUE NOT NULL
);

DROP TABLE IF EXISTS posts_tags;
CREATE TABLE posts_tags(
	post_id INT NOT NULL,
	tag_id  INT NOT NULL
);
DROP INDEX IF EXISTS post_tag_index;
CREATE UNIQUE INDEX post_tag_index ON posts_tags(post_id, tag_id);

