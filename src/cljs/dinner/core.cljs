(ns dinner.core
  (:require-macros [secretary.core :refer [defroute]])
  (:require [cljsjs.react :as react]
            [goog.events :as events]
            [reagent.core :as reagent :refer [atom]]
            [reagent.session :as session]
            [re-frame.core :refer [dispatch dispatch-sync]]
            [secretary.core :as secretary]
            [dinner.handlers]          ;; appears unused, but you need it here, to force loading.
            [dinner.subs]              ;; appears unused, but you need it here, to force loading.
            [dinner.views])
  (:import [goog History]
           [goog.history EventType]))

(enable-console-print!)
;; -- Routing -----------------------------------------------------------------
;; See example routing in the todomvc sample:
;; https://github.com/Day8/re-frame/blob/develop/examples/todomvc/src/todomvc/core.cljs#L18-L25

(defn mount-root
  []
  ; var-quote here to fix reload: #'dinner.views/dinner-app
  ; You'll need to refresh after adding the var-quote
  (reagent/render [dinner.views/dinner-app] (.getElementById js/document "app")))

;; -- Initialize app ----------------------------------------------------------
;;
;; See these notes:
;; https://github.com/Day8/re-frame/wiki/Bootstrap-An-Application
;;
(defn init! []
  (dispatch-sync [:initialise-db])
  (mount-root))
