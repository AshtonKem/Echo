(ns echo.core-test
  (:require [clojure.test :refer :all]
            [echo.core :refer :all]))

(deftest basic-tests
  (testing "Calls the underlying function"
    (let [state (atom false)]
      (echo (fn [x] (reset! state true)) {})
      (is (= @state true))))
  (testing "Records nothing when not in a canonical example"
    (echo identity {})
    (is (= {} @requests))))
