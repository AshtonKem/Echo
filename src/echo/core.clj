(ns echo.core)

(def requests (atom {}))

(def ^:dynamic *canonical-example* nil)

(defn echo [handler request]
  (let [result (handler request)]
    (when-not (nil? *canonical-example*)
      (swap! requests assoc *canonical-example* {:request request :response result}))
    result))

(defmacro canonical-example-for [name & body]
  `(binding [*canonical-example* ~name]
     ~@body))

(defn reset-canonical-examples!
  ([] (reset-canonical-examples! (fn [])))
  ([f]
    (reset! requests {})
    (f)))
