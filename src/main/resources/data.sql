INSERT INTO sources (path, type, is_random, black_list, black_url, description) VALUES
('stoloto', 'META', TRUE, '113.161.13.66',
 'https://stoloto.ru/?ad=justlink&utm_source=justlink&utm_medium=cpa&utm_content=testik&utm_campaign=p0', NULL);

INSERT INTO destinations (id, source_path, domain, url, by_default) VALUES
('5dbaa378-13b6-453c-b88d-10e40a58ce55', 'stoloto', 'stoloto.ru',
 'https://stoloto.ru/?ad=justlink&utm_source=justlink&utm_medium=cpa&utm_campaign=vk', TRUE);

INSERT INTO parameters (domain, src_name, dest_name) VALUES ('stoloto.ru', 'url', 'click_id');
INSERT INTO parameters (domain, src_name, dest_name) VALUES ('stoloto.ru', 'utm_campaign', 'utm_campaign');
INSERT INTO parameters (domain, src_name, dest_name) VALUES ('stoloto.ru', 'utm_content', 'utm_content');
INSERT INTO parameters (domain, src_name, dest_name) VALUES ('stoloto.ru', 'utm_keyword', 'utm_keyword');
INSERT INTO parameters (domain, src_name, dest_name) VALUES ('stoloto.ru', 'utm_medium', 'utm_medium');
INSERT INTO parameters (domain, src_name, dest_name) VALUES ('stoloto.ru', 'utm_source', 'utm_source');
INSERT INTO parameters (domain, src_name, dest_name) VALUES ('stoloto.ru', 'utm_term', 'utm_term');
