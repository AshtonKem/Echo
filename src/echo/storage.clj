(ns echo.storage
  (:require [echo.core :refer [requests]]
            [clojure.java.io :refer [make-parents writer]]
            [clojure.pprint :refer [pprint]]))

(defn store-edn [prefix]
  (doall (map (fn [[sample-name values]]
                (let [filename (str prefix "/" (name sample-name) ".request")]
                  (make-parents filename)
                  (with-open [wrtr (writer filename)]
                    (.write wrtr (with-out-str (pprint values))))))
              @requests)))
