(ns dinner.views
  (:require [re-frame.core :refer [subscribe dispatch]]
            [reagent.core :as reagent :refer [atom]]))

(defn dinner-app
  []
  (let [number (subscribe [:number])]
    (fn []
      [:div "hello " @number])))
