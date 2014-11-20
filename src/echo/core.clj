(ns echo.core)

(def requests (atom {}))

(def ^:dynamic *canonical-example* nil)

(defn echo [handler request]
  (handler request))
