package Game;

public class Card {
	int idinfield, attack, attackdefault, defense, maxdefense, type, cost,
			attackable, maxattackable;
	boolean active;

	Player player;

	void attackTarget(Card target) {
		if(attackable<1){
			System.out.println("공격 불가능합니다.");
			return;
		}
		attackable--;
		defense -= target.attack;
		target.defense -= attack;
		checkWinner();
	}

	void checkWinner() {
		if (player.king.defense <= 0 && player.enemy.king.defense <= 0) {
			System.out.println("draw");
		} else if (player.king.defense <= 0) {
			System.out.println("You Lose");
		} else if (player.enemy.king.defense <= 0) {
			System.out.println("You Win");
		}
	};
}
