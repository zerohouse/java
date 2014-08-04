public class Card {
	static final String SEPERATOR = "@%@";
	
	int index, attack, attackdefault, defense, maxdefense, type, cost,
			attackable, maxattackable;
	boolean active;
	String name, description;

	Player player;
	

	public Card(int attack, int defense, int cost, Player player) {
		this.player = player;
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

	void attackTarget(Card target, int thisnum, int targetnum) {
		if (attackable < 1) {
			System.err.println("선택한 대상은 공격 능력이 없습니다.");
			return;
		}
		attackable--;
		defense -= target.attack;
		target.defense -= attack;
		checkWinner();
		checkAlive(target, thisnum, targetnum);
	}

	private void checkAlive(Card target, int thisnum, int targetnum) {
		if (defense < 1) {
			player.field.remove(thisnum);
		}
		if (target.defense < 1) {
			target.player.field.remove(targetnum);
		}

	}

	void checkWinner() {
		if (player.king.defense < 1 && player.enemy.king.defense < 1) {
			System.out.println("draw");
		} else if (player.king.defense <= 0) {
			System.out.println("You Lose");
		} else if (player.enemy.king.defense <= 0) {
			System.out.println("You Win");
		}
	}
}

