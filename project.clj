(defproject clj-blog "0.1.0-SNAPSHOT"
  :description "Clojure Blog Platform"
  :url "https://github.com/rhowell/clj-blog"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.6"]
                 [ring/ring-devel "1.3.0"]
                 [ring/ring-core "1.3.0"]
                 [org.clojure/java.jdbc "0.3.2"]
                 [com.cemerick/friend "0.2.1"]
                 [postgresql "9.3-1101.jdbc4"]
                 [http-kit "2.1.16"]]
  :plugins [[lein-ring "0.8.11"]]
  :ring {:handler clj-blog.handler/app}
  :profiles 
  {:dev {
   :dependencies [[javax.servlet/servlet-api "2.5"]
                  [ring-mock "0.1.5"]]
   :kapooya {:environment "development"
             :db-spec {:subprotocol "postgresql"
                       :subname "//localhost:5432/clj_blog"
                       :user "blog_user" }}}}
  :main clj-blog.handler)
