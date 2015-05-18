(ns dinner.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [register-sub subscribe]]))


;; -- Helpers -----------------------------------------------------------------


;; -- Subscription handlers and registration  ---------------------------------

(defn number [db]
  (reaction 2))
(register-sub :number number)
