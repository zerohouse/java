public class Card {
	int index, attack, attackdefault, defense, maxdefense, type, cost,
			attackable, maxattackable;
	boolean active;
	String name,description;

	Player player;

	public Card(int attack, int defense, int cost) {
		this.attack = attack;
		this.attackdefault = attack;
		this.defense = defense;
		this.maxdefense = defense;
		this.type = 0; // 0은 기본하수인.
		this.cost = cost;
		this.attackable = 0;
		this.maxattackable = 1;
		this.active = false;
	}

	void attackTarget(Card target) {
		if(attackable<1){
			System.out.println("공격 불가능합니다.");
			return;
		}
		attackable--;
		defense -= target.attack;
		target.defense -= attack;
		checkWinner();
		checkAlive(target);
	}

	private void checkAlive(Card target) {
		if(defense<1){
			//player.removeCard(this);
		}
		if (target.defense<1){
			//target.player.removeCard(target);
		}
		
	}

	void checkWinner() {
		if (player.king.defense <= 0 && player.enemy.king.defense <= 0) {
			System.out.println("draw");
		} else if (player.king.defense <= 0) {
			System.out.println("You Lose");
		} else if (player.enemy.king.defense <= 0) {
			System.out.println("You Win");
		}
	}
}
