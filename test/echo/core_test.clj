(ns echo.core-test
  (:require [clojure.test :refer :all]
            [echo.core :refer :all]))

(use-fixtures :each reset-canonical-examples!)

(deftest basic-test
  (testing "Calls the underlying function"
    (let [state (atom false)]
      (echo (fn [x] (reset! state true)) {})
      (is (= @state true))))
  (testing "Returns what we expect"
    (is (= {:foo :bar}
           (echo (constantly {:foo :bar})
                 {:bar :baz})))))

(deftest recording-test
  (testing "Records nothing when not in a canonical example"
    (echo identity {})
    (is (= {} @requests)))
  (testing "Records changes with canonical-example"
    (canonical-example-for "foo"
                           (echo identity {:bar "baz"}))
    (is (= {"foo" {:request {:bar "baz"}
                   :response {:bar "baz"}}} @requests)))
  (testing "reset-canonical-examples!"
    (reset-canonical-examples!)
    (is (= {} @requests))))