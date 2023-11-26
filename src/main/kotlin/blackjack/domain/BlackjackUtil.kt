package blackjack.domain

import blackjack.domain.CardNumberInfo.Companion.ACE_UPPER_SCORE

object BlackjackUtil {
    private const val BLACKJACK_SCORE = 21

    fun computeScore(hand: Hand): Pair<Int, Int> {
        val minScore = hand.sumOf()
        val maxScore = minScore.minus(1).plus(ACE_UPPER_SCORE)

        return if (hand.hasAce() && maxScore <= BLACKJACK_SCORE) {
            Pair(minScore, maxScore)
        } else {
            Pair(minScore, minScore)
        }
    }

    fun isBust(score: Int): Boolean = score > BLACKJACK_SCORE

    fun winAgainstDealer(playerScore: Int, dealerScore: Int): Boolean? {
        return when {
            isBust(dealerScore) -> true
            isBust(playerScore) -> false
            playerScore == dealerScore -> null
            else -> playerScore > dealerScore
        }
    }
}
