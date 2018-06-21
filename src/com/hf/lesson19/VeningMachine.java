package com.hf.lesson19;

import java.util.EnumMap;
import java.util.Iterator;

import com.hf.lesson15.Generator;
import com.hf.lesson18.TextFile;

// 自动售卖机
public class VeningMachine {
	private static  State state = State.RESTING;
	private static int amount = 0;
	private static Input selection = null;
	enum StateDuration {TRANSIENT}
	enum State {
		RESTING {

			@Override
			void next(Input input) {
				switch(Category.categorize(input)) {
				case MONEY:
					amount += input.amount();
					state = ADDING_MONEY;
					break;
				case SHUT_DOWN:
					state = TERMINAL;
				default:
				}
			}
			
		},
		ADDING_MONEY {

			@Override
			void next(Input input) {
				switch(Category.categorize(input)) {
				case MONEY:
					amount += input.amount();
					break;
				case ITEM_SELECTION:
					selection = input;
					if(amount <  selection.amount()) {
						System.out.println("Insufficient money for "+selection);
					}else {
						state = State.DISPENSING;
					}
					break;
				case QUIT_TRANSACTION:
					state = State.GIVING_CHANGE;
					break;
				case SHUT_DOWN:
					state = TERMINAL;
				default:
				}
			}
			
		},
		DISPENSING(StateDuration.TRANSIENT) {
			void next() {
				System.out.println("here is your "+selection);
				amount -= selection.amount();
				state = State.GIVING_CHANGE;
			}
		},
		GIVING_CHANGE(StateDuration.TRANSIENT) {
			void next() {
				if(amount > 0) {
					System.out.println("Your change： "+amount);
					amount = 0;
				}
				state = State.RESTING;
			}
		},
		TERMINAL {
			void output() {
				System.out.println("Halted..");
			}
		};
		private boolean isTransient = false;
		State(){}
		State(StateDuration trans){
			isTransient = true;
		}
		void next() {
			throw new RuntimeException("next........empty..");
		}
		void next(Input input) {
			throw new RuntimeException("next.....input.....");
		}
		void output() {
			System.out.println(amount);
		}
	}
	
	static void run(Generator<Input> gen) {
		while(state != State.TERMINAL) {
			state.next(gen.next());
			while(state.isTransient) {
				state.next();
			}
			state.output();
		}
	}
	
	public static void main(String[] args) {
		Generator<Input> gen = new RandomInputGenerator();
		run(gen);
	}
}
class RandomInputGenerator implements Generator<Input> {

	@Override
	public Input next() {
		return Input.randomSelection();
	}
}

class FileInputGenerator implements Generator<Input> {
	private Iterator<String> input;
	
	public FileInputGenerator(String filename) {
		input  = new TextFile(filename,";").iterator();
	}
	
	@Override
	public Input next() {
		if(!input.hasNext()) {
			return null;
		}
		return Enum.valueOf(Input.class, input.next().trim());
	}
	
}
enum Category {
	MONEY(Input.NICKEL, Input.DIME, Input.QUARTER, Input.DOLLAR),
	ITEM_SELECTION(Input.TOOTHPASTE, Input.CHIPS, Input.SODA, Input.SOAP),
	QUIT_TRANSACTION(Input.ABORT_TRANSACTION),
	SHUT_DOWN(Input.STOP);
	
	private Input[] values;
	
	Category(Input...types){
		values = types;
	}
	
	private static EnumMap<Input,Category> categories = new EnumMap<>(Input.class);
	
	static {
		for(Category c : Category.class.getEnumConstants()) {
			for(Input in : c.values) {
				categories.put(in, c);
			}
		}
	}
	
	public static Category categorize(Input input) {
		return categories.get(input);
	}
}
