(ns clj-blog.handler
  (:use compojure.core
        ring.util.response
        [compojure.handler :only [site]]
        [org.httpkit.server :only [run-server]])
  (:require [ring.middleware.reload :as reload]
            [ring.middleware.json :as json]
            [compojure.route :as route]))

(defroutes app-routes
  (GET "/" [] (response {:status 200
                         :body "How's it going?"}))
  (route/resources "/")
  (route/not-found (response {:status 404
                              :body "Not Found"})))

(defn in-dev? [& args] true) ;; TODO read a config variable from command line, env, or file?

(defn -main [& args] ;; entry point, lein run will pick up and start from here
  (let [handler (-> (if (in-dev? args)
                      (reload/wrap-reload (site #'app-routes)) ;; only reload when dev
                      (site app-routes))
                    (json/wrap-json-body)
                    (json/wrap-json-response))]
        (run-server handler {:port 8080})))
