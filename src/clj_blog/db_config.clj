(ns clj-blog.db-config
  (:use korma.db
        korma.core))

(defdb db (postgres {:db "clj_blog"
                     :user "blog_user"
                     :host "127.0.0.1"
                     :port "5432"}))


(declare users posts tags)

(defentity users
  (has-many posts))

(defentity posts
  (belongs-to users)
  (many-to-many tags :users_posts))
  
(defentity tags
  (many-to-many posts :users_posts))
