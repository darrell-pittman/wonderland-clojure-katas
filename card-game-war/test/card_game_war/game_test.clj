(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all]))


;; fill in  tests for your game
(deftest test-play-round
  (testing "the highest rank wins the cards in the round"
    (is (= [:spade 3] (play-round [:spade 3] [:spade 2])))
    (is (= [:spade 3] (play-round [:spade 3] [:heart 2])))
    (is (= [:spade 3] (play-round [:spade 3] [:club 2])))
    (is (= [:spade 3] (play-round [:spade 3] [:diamond 2]))))
  (testing "queens are higher rank than jacks"
    (is (= [:spade :queen] (play-round [:spade :queen] [:spade :jack] ))))
  (testing "kings are higher rank than queens"
    (is (= [:spade :king] (play-round [:spade :queen] [:spade :king] ))))
  (testing "aces are higher rank than kings"
    (is (= [:spade :ace] (play-round [:spade :ace] [:spade :king] ))))
  (testing "if the ranks are equal, clubs beat spades"
    (is (= [:club :ace] (play-round [:spade :ace] [:club :ace] ))))
  (testing "if the ranks are equal, diamonds beat clubs"
    (is (= [:diamond :ace] (play-round [:diamond :ace] [:club :ace] ))))
  (testing "if the ranks are equal, hearts beat diamonds")
    (is (= [:heart :ace] (play-round [:diamond :ace] [:heart :ace] ))))

(deftest test-play-game
  (testing "the player loses when they run out of cards"
    (let [[p1 p2] (apply play-game (deal))]
      (is (or (empty? p1) (empty? p2))))))


