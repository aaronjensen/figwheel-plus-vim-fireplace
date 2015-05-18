(ns dinner.repl
  (:require
    [cemerick.piggieback :as piggieback]
    weasel.repl.websocket
    weasel.repl.server
    [dinner.handler :refer :all]
    [dinner.dev :refer :all]
    [ring.server.standalone :refer :all]
    [ring.middleware.file-info :refer :all]
    [ring.middleware.file :refer :all]))

(defonce server (atom nil))

(defn get-handler []
  ;; #'app expands to (var app) so that when we reload our code,
  ;; the server is forced to re-resolve the symbol in the var
  ;; rather than having its own copy. When the root binding
  ;; changes, the server picks it up without having to restart.
  (-> #'app
      ; Makes static assets in $PROJECT_DIR/resources/public/ available.
      (wrap-file "resources")
      ; Content-Type, Content-Length, and Last Modified headers for files in body
      (wrap-file-info)))

(defn start-server
  "used for starting the server in development mode from REPL"
  [& [port]]
  (let [port (if port (Integer/parseInt port) 4000)]
    (reset! server
            (serve (get-handler)
                   {:port port
                    :auto-reload? true
                    :join? false}))
    (println (str "You can view the site at http://localhost:" port))))

(defn stop-server []
  (.stop @server)
  (reset! server nil))

(defn repl-env []
  (weasel.repl.server/stop)
  (weasel.repl.websocket/repl-env :ip "0.0.0.0" :port 9001))

(defn browser-repl []
  (piggieback/cljs-repl (repl-env)))

(defn run []
  (start-server)
  (start-figwheel))
