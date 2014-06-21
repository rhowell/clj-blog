(ns clj-blog.handler
  (:use compojure.core
        [compojure.handler :only [site]]
        [org.httpkit.server :only [run-server]])
  (:require [ring.middleware.reload :as reload]
            [compojure.route :as route]))

(defroutes app-routes
  (GET "/" [] "Hello People")
  (route/resources "/")
  (route/not-found "Not Found"))

(defn in-dev? [& args] true) ;; TODO read a config variable from command line, env, or file?

(defn -main [& args] ;; entry point, lein run will pick up and start from here
  (let [handler (if (in-dev? args)
                  (reload/wrap-reload (site #'app-routes)) ;; only reload when dev
                  (site app-routes))]
    (run-server handler {:port 8080})))
