(ns dinner.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [register-sub subscribe]]))


;; -- Helpers -----------------------------------------------------------------


;; -- Subscription handlers and registration  ---------------------------------

; Change this number while figwheel is running.
; It won't update unless you var-quote dinner.views/dinner-app
(defn number [db]
  (reaction 3))
(register-sub :number number)
