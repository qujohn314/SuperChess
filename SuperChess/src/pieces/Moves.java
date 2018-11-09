package pieces;

import java.awt.Color;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;


import gui.Board;

public class Moves {
	
	private Consumer<Board> spaceUpdate,fakeSpaceUpdate,spots;
	private BooleanSupplier validity;

	private Moves(Consumer<Board> real,Consumer<Board> fake,BooleanSupplier valid,Consumer<Board> moves) {
		spaceUpdate = real;
		fakeSpaceUpdate = fake;
		validity = valid;
		spots = moves;
	}
	
	public void updateMoves(Board b) {
		spaceUpdate.accept(b);
	}
	
	public void updateFakeMoves(Board b) {
		fakeSpaceUpdate.accept(b);
	}
	
	public boolean validSpaces() {
		return validity.getAsBoolean();
	}
	
	public void getMoves(Board b) {
		spots.accept(b);
	}
	
	public static void test() {
		
	}
	
	public static class Move {
		
		/**
		 * Orthogonal Movement
		 * 
		 * @notation -1 = no direction | 0 = to end | anything else = amount of spaces moved
		 * @param p - The piece being moved / checked
		 * @param n - The orthogonal moves. In order of Right,Up,Left,Down
		 * @return orthoganal moves
		 */
		public static Moves Orthogonal(Piece p,int...n) {
			return new Moves(br -> { //UPDATING REAL BOARD
				if(!(n[0] < 0)) {
					if(n[0] == 0) {
						for(int cc = p.c+1;cc<Board.getBoard()[0].length;cc++) {
							if(Board.getBoard()[p.r][cc].getFill()) {
								if(cc+1 < Board.getBoard()[0].length && Board.getBoard()[p.r][cc].getPiece() instanceof King)
				                    Board.getBoard()[p.r][cc+1].setDanger(p);
				                break;
							}
							else
								Board.getBoard()[p.r][cc].setDanger(p);
						}
					}else {
						for(int i = 1;i<=n[0] && p.c+i < Board.getBoard()[0].length;i++)
							if(p.c+i < Board.getBoard()[0].length)
								if(Board.getBoard()[p.r][p.c+i].getFill()) {
									if(p.c+i+1 < Board.getBoard()[0].length && Board.getBoard()[p.r][p.c+i].getPiece() instanceof King)
					                    Board.getBoard()[p.r][p.c+i+1].setDanger(p);
					                break;
								}
								else
									Board.getBoard()[p.r][p.c+i].setDanger(p);
					}
				   
				}
				if(!(n[1] < 0)) {
					if(n[1] == 0) {
						for(int rr = p.r-1;rr>=0;rr--)
							if(Board.getBoard()[rr][p.c].getFill()) {
								if(rr-1 >=0 && Board.getBoard()[rr][p.c].getPiece() instanceof King)
				                    Board.getBoard()[rr-1][p.c].setDanger(p);
				                break;
							}
							else
								Board.getBoard()[rr][p.c].setDanger(p);
					}else {
						for(int i = 1;i<=n[1] && p.r-i >=0;i++)
							if(p.r-i >= 0)
								if(Board.getBoard()[p.r-i][p.c].getFill()) {
									if(p.r-i-1 >= 0 && Board.getBoard()[p.r-i][p.c].getPiece() instanceof King)
					                    Board.getBoard()[p.r-i-1][p.c].setDanger(p);
					                break;
								}
								else
									Board.getBoard()[p.r-i][p.c].setDanger(p);
					}
				   
				}
				if(!(n[2] < 0)) {
					if(n[2] == 0) {
						for(int cc = p.c-1;cc>=0;cc--) {
							if(Board.getBoard()[p.r][cc].getFill()) {
								if(cc-1 >= 0 && Board.getBoard()[p.r][cc].getPiece() instanceof King)
				                    Board.getBoard()[p.r][cc-1].setDanger(p);
				                break;
							}
							else
								Board.getBoard()[p.r][cc].setDanger(p);
						}
					}else {
						for(int i = 1;i<=n[2] && p.c-i>=0;i++)
							if(p.c-i >= 0)
								if(Board.getBoard()[p.r][p.c-i].getFill()) {
									if(p.c-i-1 >=0 && Board.getBoard()[p.r][p.c-i].getPiece() instanceof King)
					                    Board.getBoard()[p.r][p.c-i-1].setDanger(p);
					                break;
								}
								else
									Board.getBoard()[p.r][p.c-i].setDanger(p);
					}
				   
				}
				if(!(n[3] < 0)) {
					if(n[3] == 0) {
						for(int rr = p.r+1;rr<Board.getBoard().length;rr++)
							if(Board.getBoard()[rr][p.c].getFill()) {
								if(rr+1 <Board.getBoard().length && Board.getBoard()[rr][p.c].getPiece() instanceof King)
				                    Board.getBoard()[rr+1][p.c].setDanger(p);
				                break;
							}
							else
								Board.getBoard()[rr][p.c].setDanger(p);
					}else {
						for(int i = 1;i<=n[3] && p.r+i<Board.getBoard().length;i++)
							if(p.r+i < Board.getBoard().length)
								if(Board.getBoard()[p.r+i][p.c].getFill()) {
									if(p.r+i+1 < Board.getBoard().length && Board.getBoard()[p.r+i][p.c].getPiece() instanceof King)
					                    Board.getBoard()[p.r+i+1][p.c].setDanger(p);
					                break;
								}
								else
									Board.getBoard()[p.r+i][p.c].setDanger(p);
					}
				}	
			}, bf ->  { //UPDATING HIDDEN BOARD
				if(!(n[0] < 0)) {
					if(n[0] == 0) {
						for(int cc = p.c+1;cc<Board.getDangerBoard()[0].length;cc++) {
							if(Board.getDangerBoard()[p.r][cc].getFill()) {
								Board.getDangerBoard()[p.r][cc].setDanger(p);
								if(cc+1 < Board.getDangerBoard()[0].length && Board.getDangerBoard()[p.r][cc].getPiece() instanceof King)
				                    Board.getDangerBoard()[p.r][cc+1].setDanger(p);
				                break;
							}
							else
								Board.getDangerBoard()[p.r][cc].setDanger(p);
						}
					}else {
						for(int i = 1;i<=n[0];i++)
							if(p.c+i < Board.getDangerBoard()[0].length)
								if(Board.getDangerBoard()[p.r][p.c+i].getFill()) {
									if(p.c+i+1 < Board.getDangerBoard()[0].length && Board.getDangerBoard()[p.r][p.c+i].getPiece() instanceof King)
					                    Board.getDangerBoard()[p.r][p.c+i+1].setDanger(p);
					                break;
								}
								else
									Board.getDangerBoard()[p.r][p.c+i].setDanger(p);
					}
				   
				}
				if(!(n[1] < 0)) {
					if(n[1] == 0) {
						for(int rr = p.r-1;rr>=0;rr--)
							if(Board.getDangerBoard()[rr][p.c].getFill()) {
								Board.getDangerBoard()[rr][p.c].setDanger(p);
								if(rr-1 >=0 && Board.getDangerBoard()[rr][p.c].getPiece() instanceof King)
				                    Board.getDangerBoard()[rr-1][p.c].setDanger(p);
				                break;
							}
							else
								Board.getDangerBoard()[rr][p.c].setDanger(p);
					}else {
						for(int i = 1;i<=n[1];i++)
							if(p.r-i >= 0)
								if(Board.getDangerBoard()[p.r-i][p.c].getFill()) {
									if(p.r-i-1 >= 0 && Board.getDangerBoard()[p.r-i][p.c].getPiece() instanceof King)
					                    Board.getDangerBoard()[p.r-i-1][p.c].setDanger(p);
					                break;
								}
								else
									Board.getDangerBoard()[p.r-i][p.c].setDanger(p);
					}
				}
				if(!(n[2] < 0)) {
					if(n[2] == 0) {
						for(int cc = p.c-1;cc>=0;cc--) {
							if(Board.getDangerBoard()[p.r][cc].getFill()) {
								Board.getDangerBoard()[p.r][cc].setDanger(p);
								if(cc-1 >= 0 && Board.getDangerBoard()[p.r][cc].getPiece() instanceof King)
				                    Board.getDangerBoard()[p.r][cc-1].setDanger(p);
				                break;
							}
							else
								Board.getDangerBoard()[p.r][cc].setDanger(p);
						}
					}else {
						for(int i = 1;i<=n[2];i++)
							if(p.c-i >= 0)
								if(Board.getDangerBoard()[p.r][p.c-i].getFill()) {
									if(p.c-i-1 >=0 && Board.getDangerBoard()[p.r][p.c-i].getPiece() instanceof King)
					                    Board.getDangerBoard()[p.r][p.c-i-1].setDanger(p);
					                break;
								}
								else
									Board.getDangerBoard()[p.r][p.c-i].setDanger(p);
					}
				   
				}
				if(!(n[3] < 0)) {
					if(n[3] == 0) {
						for(int rr = p.r+1;rr<Board.getDangerBoard().length;rr++)
							if(Board.getDangerBoard()[rr][p.c].getFill()) {
								Board.getDangerBoard()[rr][p.c].setDanger(p);
								if(rr+1 <Board.getDangerBoard().length && Board.getDangerBoard()[rr][p.c].getPiece() instanceof King)
				                    Board.getDangerBoard()[rr+1][p.c].setDanger(p);
				                break;
							}
							else
								Board.getDangerBoard()[rr][p.c].setDanger(p);
					}else {
						for(int i = 1;i<=n[3];i++)
							if(p.r+i < Board.getDangerBoard().length)
								if(Board.getDangerBoard()[p.r+i][p.c].getFill()) {
									if(p.r+i+1 < Board.getDangerBoard().length && Board.getDangerBoard()[p.r+i][p.c].getPiece() instanceof King)
					                    Board.getDangerBoard()[p.r+i+1][p.c].setDanger(p);
					                break;
								}
								else
									Board.getDangerBoard()[p.r+i][p.c].setDanger(p);
					}
				}
				
			}, () -> { //CHECKING FOR VALID MOVES
			  if(!(n[1] < 0)) {
				if(n[1] == 0)  {
				 for(int rr = p.r-1;rr>=0;rr--){
			            if(p.isValidMove(rr,p.c))
			                if(Board.getBoard()[rr][p.c].getFill() == true){
			                    if(Board.getBoard()[rr][p.c].getPiece().getTeam() != p.team){
			                        return true;
			                    }
			                    break;
			                }else{
			                    return true;
			                }
			            else if(Board.getBoard()[rr][p.c].getFill() == true)
			                break;
			        }
				}else {
					for(int i = 1;i<=n[1];i++){
			            if(p.isValidMove(p.r+i,p.c))
			                if(Board.getBoard()[p.r+i][p.c].getFill() == true){
			                    if(Board.getBoard()[p.r+i][p.c].getPiece().getTeam() != p.team){
			                        return true;
			                    }
			                    break;
			                }else{
			                    return true;
			                }
			            else if(Board.getBoard()[p.r+i][p.c].getFill() == true)
			                break;
			        }
				}
			  }if(!(n[3] < 0)) {
					if(n[3] == 0) {
			        for(int rr = p.r+1;rr<Board.getBoard().length;rr++){
			            if(p.isValidMove(rr,p.c))
			                if(Board.getBoard()[rr][p.c].getFill() == true){
			                    if(Board.getBoard()[rr][p.c].getPiece().getTeam() != p.team){
			                        return true;
			                    }
			                    break;
			                }else{
			                    return true;
			                }
			            else if(Board.getBoard()[rr][p.c].getFill() == true)
			                break;
			        }
				  }else {
					  for(int i = 1;i<=n[3];i++){
				            if(p.isValidMove(p.r+i,p.c))
				                if(Board.getBoard()[p.r+i][p.c].getFill() == true){
				                    if(Board.getBoard()[p.r+i][p.c].getPiece().getTeam() != p.team){
				                        return true;
				                    }
				                    break;
				                }else{
				                    return true;
				                }
				            else if(Board.getBoard()[p.r+i][p.c].getFill() == true)
				                break;
				        }
				  }
			    }
			    if(!(n[2] < 0)) {
					if(n[2] == 0) {
			          for(int cc = p.c-1;cc>=0;cc--){
			            if(p.isValidMove(p.r,cc))
			                if(Board.getBoard()[p.r][cc].getFill() == true){
			                    if(Board.getBoard()[p.r][cc].getPiece().getTeam() != p.team){
			                        return true;
			                    }
			                    break;
			                }else{
			                    return true;
			                }
			            else if(Board.getBoard()[p.r][cc].getFill() == true)
			                break;
			        }
				  }else {
					  for(int i = 1;i<=n[2];i++){
				            if(p.isValidMove(p.r,p.c-i))
				                if(Board.getBoard()[p.r][p.c-i].getFill() == true){
				                    if(Board.getBoard()[p.r][p.c-i].getPiece().getTeam() != p.team){
				                        return true;
				                    }
				                    break;
				                }else{
				                    return true;
				                }
				            else if(p.c-i >= 0 &&Board.getBoard()[p.r][p.c-i].getFill() == true)
				                break;
				        }
				   }
			     }
			     if(!(n[0] < 0)) {
					if(n[0] == 0) {
			        for(int cc = p.c+1;cc<Board.getBoard()[0].length;cc++){
			            if(p.isValidMove(p.r,cc))
			                if(Board.getBoard()[p.r][cc].getFill() == true){
			                    if(Board.getBoard()[p.r][cc].getPiece().getTeam() != p.team){
			                        return true;
			                    }
			                    break;
			                }else{
			                    return true;
			                }
			            else if(Board.getBoard()[p.r][cc].getFill() == true)
			                break;
			        }
			        	
					}else {
						for(int i = 1;i<=n[0];i++){
				            if(p.isValidMove(p.r,p.c+i))
				                if(Board.getBoard()[p.r][p.c+i].getFill() == true){
				                    if(Board.getBoard()[p.r][p.c+i].getPiece().getTeam() != p.team){
				                        return true;
				                    }
				                    break;
				                }else{
				                    return true;
				                }
				            else if(p.c+i < Board.getBoard()[0].length&& Board.getBoard()[p.r][p.c+i].getFill() == true)
				                break;
				        }
					}
				}return false;}, s -> { //GETTING MOVES
				  if(!(n[1] < 0)) {
					 if(n[1] == 0)  {
					 for(int rr = p.r-1;rr>=0;rr--){
			            if(p.isValidMove(rr,p.c)) {
			                if(Board.getBoard()[rr][p.c].getFill() == true){
			                    if(Board.getBoard()[rr][p.c].getPiece().getTeam() != p.team){
			                        Board.getBoard()[rr][p.c].setBackground(Color.RED);
			                    }
			                    break;
			                }else{
			                    Board.getBoard()[rr][p.c].setBackground(Color.RED);
			                }
			            }else if(Board.getBoard()[rr][p.c].getFill() == true)
			                break;
			          }
				    }else {
				    	for(int i = 1;i<n[1];i++){
				            if(p.isValidMove(p.r-i,p.c)) {
				                if(Board.getBoard()[p.r-i][p.c].getFill() == true){
				                    if(Board.getBoard()[p.r-i][p.c].getPiece().getTeam() != p.team){
				                        Board.getBoard()[p.r-i][p.c].setBackground(Color.RED);
				                    }
				                    break;
				                }else{
				                    Board.getBoard()[p.r-i][p.c].setBackground(Color.RED);
				                }
				            }else if(p.r-i >= 0 && Board.getBoard()[p.r-i][p.c].getFill() == true)
				                break;
				          }
				    }
				  }
				  if(!(n[3] < 0)) {
					if(n[3] == 0)  {
			         for(int rr = p.r+1;rr<Board.getBoard().length;rr++){
			            if(p.isValidMove(rr,p.c)){
			                if(Board.getBoard()[rr][p.c].getFill() == true){
			                    if(Board.getBoard()[rr][p.c].getPiece().getTeam() != p.team){
			                        Board.getBoard()[rr][p.c].setBackground(Color.RED);
			                    }
			                    break;
			                }
			                else{
			                    Board.getBoard()[rr][p.c].setBackground(Color.RED);
			                }
			            }else if(Board.getBoard()[rr][p.c].getFill() == true)
			                break;
			         }
				    }else {
				    	for(int i = 1;i<n[3];i++){
				            if(p.isValidMove(p.r+i,p.c)) {
				                if(Board.getBoard()[p.r+i][p.c].getFill() == true){
				                    if(Board.getBoard()[p.r+i][p.c].getPiece().getTeam() != p.team){
				                        Board.getBoard()[p.r+i][p.c].setBackground(Color.RED);
				                    }
				                    break;
				                }else{
				                    Board.getBoard()[p.r+i][p.c].setBackground(Color.RED);
				                }
				            }else if(p.r+i < Board.getBoard().length && Board.getBoard()[p.r+i][p.c].getFill() == true)
				                break;
				          }
				    	}
				  }
				  if(!(n[2] < 0)) {
					if(n[2] == 0)  {
			         for(int cc = p.c-1;cc>=0;cc--){
			            if(p.isValidMove(p.r,cc)){
			                if(Board.getBoard()[p.r][cc].getFill() == true){
			                    if(Board.getBoard()[p.r][cc].getPiece().getTeam() != p.team){
			                        Board.getBoard()[p.r][cc].setBackground(Color.RED);
			                    }
			                    break;
			                }else{
			                    Board.getBoard()[p.r][cc].setBackground(Color.RED);
			                }
			            }else if(Board.getBoard()[p.r][cc].getFill() == true)
			                break;
			        }
				   }else {
					   for(int i = 1;i<n[2];i++){
				            if(p.isValidMove(p.r,p.c-i)) {
				                if(Board.getBoard()[p.r][p.c-i].getFill() == true){
				                    if(Board.getBoard()[p.r][p.c-i].getPiece().getTeam() != p.team){
				                        Board.getBoard()[p.r][p.c-i].setBackground(Color.RED);
				                    }
				                    break;
				                }else{
				                    Board.getBoard()[p.r][p.c-i].setBackground(Color.RED);
				                }
				            }else if(Board.getBoard()[p.r][p.c-i].getFill() == true)
				                break;
				          }
				    }
				  }
				  if(!(n[0] < 0)) {
				    if(n[0] == 0)  {
			         for(int cc = p.c+1;cc<Board.getBoard()[0].length;cc++){
			            if(p.isValidMove(p.r,cc)){
			                if(Board.getBoard()[p.r][cc].getFill() == true){
			                    if(Board.getBoard()[p.r][cc].getPiece().getTeam() != p.team){
			                        Board.getBoard()[p.r][cc].setBackground(Color.RED);
			                    }
			                    break;
			                }else{
			                    Board.getBoard()[p.r][cc].setBackground(Color.RED);
			                }
			            }else if(Board.getBoard()[p.r][cc].getFill() == true)
			                break;
			        }
				    }else {
				    	 for(int i = 1;i<n[0];i++){
					            if(p.isValidMove(p.r,p.c+i)) {
					                if(Board.getBoard()[p.r][p.c+i].getFill() == true){
					                    if(Board.getBoard()[p.r][p.c+i].getPiece().getTeam() != p.team){
					                        Board.getBoard()[p.r][p.c+i].setBackground(Color.RED);
					                    }
					                    break;
					                }else{
					                    Board.getBoard()[p.r][p.c+i].setBackground(Color.RED);
					                }
					            }else if(Board.getBoard()[p.r][p.c+i].getFill() == true)
					                break;
					          }
				    }
				  }});
				
			
			
		}
	}
}
