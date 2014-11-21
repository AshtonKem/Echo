(ns echo.core)

(def requests (atom {}))

(def ^:dynamic *canonical-example* nil)

(defn echo [handler request]
  "Call the handler with the given request, returning the result.

   If this function is used inside of a canonical-example-for block, it will record the request and response for further use, otherwise it will simply call the function normally"
  (let [result (handler request)]
    (when-not (nil? *canonical-example*)
      (swap! requests assoc *canonical-example* {:request request :response result}))
    result))

(defmacro canonical-example-for [name & body]
  "Mark the current section as the canonical example for name"
  `(binding [*canonical-example* ~name]
     ~@body))

(defn reset-canonical-examples!
  "Clear out all canonical examples."
  ([] (reset-canonical-examples! (fn [])))
  ([f]
    (reset! requests {})
    (f)))
