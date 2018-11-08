package pieces;
import gui.Board;
import java.awt.Color;
public class Bishop extends Piece
{
    public Bishop(int r, int c,int t){
        super(r,c,t);
        if(team == 1)
            loadImage(dir + "res/images/BlackBishop.png");
        else
            loadImage(dir + "res/images/WhiteBishop.png");
    }

    public void getMoves(){
        int rr = r;
        int cc = c;
        while(rr-1 >= 0 && cc+1 < 8){
            rr--;
            cc++;
            if(isValidMove(rr,cc))
                if(Board.getBoard()[rr][cc].getFill() == true){
                    if(Board.getBoard()[rr][cc].getPiece().getTeam() != team){
                        Board.getBoard()[rr][cc].setBackground(Color.RED);
                    }
                    break;
                }else{
                    Board.getBoard()[rr][cc].setBackground(Color.RED);
                }
            else if(Board.getBoard()[rr][cc].getFill() == true)
                break;
        }
        rr = r;
        cc = c;
        while(rr+1 <8 && cc+1 < 8){
            rr++;
            cc++;
            if(isValidMove(rr,cc))
                if(Board.getBoard()[rr][cc].getFill() == true){
                    if(Board.getBoard()[rr][cc].getPiece().getTeam() != team){
                        Board.getBoard()[rr][cc].setBackground(Color.RED);
                    }
                    break;
                }else{
                    Board.getBoard()[rr][cc].setBackground(Color.RED);
                }
            else if(Board.getBoard()[rr][cc].getFill() == true)
                break;
        }
        rr = r;
        cc = c;
        while(rr-1 >= 0 && cc-1 >= 0){
            rr--;
            cc--;
            if(isValidMove(rr,cc))
                if(Board.getBoard()[rr][cc].getFill() == true){
                    if(Board.getBoard()[rr][cc].getPiece().getTeam() != team){
                        Board.getBoard()[rr][cc].setBackground(Color.RED);
                    }
                    break;
                }else{
                    Board.getBoard()[rr][cc].setBackground(Color.RED);
                }
            else if(Board.getBoard()[rr][cc].getFill() == true)
                break;
        }
        rr = r;
        cc = c;
        while(rr+1 <8 && cc-1 >= 0){
            rr++;
            cc--;
            if(isValidMove(rr,cc))
                if(Board.getBoard()[rr][cc].getFill() == true){
                    if(Board.getBoard()[rr][cc].getPiece().getTeam() != team){
                        Board.getBoard()[rr][cc].setBackground(Color.RED);
                    }
                    break;
                }else{
                    Board.getBoard()[rr][cc].setBackground(Color.RED);
                }
            else if(Board.getBoard()[rr][cc].getFill() == true)
                break;
        }
    }

    public boolean hasValidMove(){
        int rr = r;
        int cc = c;
        while(rr-1 >= 0 && cc+1 < 8){
            rr--;
            cc++;
            if(isValidMove(rr,cc))
                if(Board.getBoard()[rr][cc].getFill() == true){
                    if(Board.getBoard()[rr][cc].getPiece().getTeam() != team){
                        return true;
                    }
                    break;
                }else{
                    return true;
                }
            else if(Board.getBoard()[rr][cc].getFill() == true)
                break;
        }
        rr = r;
        cc = c;
        while(rr+1 <8 && cc+1 < 8){
            rr++;
            cc++;
            if(isValidMove(rr,cc))
                if(Board.getBoard()[rr][cc].getFill() == true){
                    if(Board.getBoard()[rr][cc].getPiece().getTeam() != team){
                        return true;
                    }
                    break;
                }else{
                    return true;
                }
            else if(Board.getBoard()[rr][cc].getFill() == true)
                break;
        }
        rr = r;
        cc = c;
        while(rr-1 >= 0 && cc-1 >= 0){
            rr--;
            cc--;
            if(isValidMove(rr,cc))
                if(Board.getBoard()[rr][cc].getFill() == true){
                    if(Board.getBoard()[rr][cc].getPiece().getTeam() != team){
                        return true;
                    }
                    break;
                }else{
                    return true;
                }
            else if(Board.getBoard()[rr][cc].getFill() == true)
                break;
        }
        rr = r;
        cc = c;
        while(rr+1 <8 && cc-1 >= 0){
            rr++;
            cc--;
            if(isValidMove(rr,cc))
                if(Board.getBoard()[rr][cc].getFill() == true){
                    if(Board.getBoard()[rr][cc].getPiece().getTeam() != team){
                        return true;
                    }
                    break;
                }else{
                    return true;
                }
            else if(Board.getBoard()[rr][cc].getFill() == true)
                break;
        }
        return false;
    }

    public void updateFakeSpaces(){
        int rr = r;
        int cc = c;
        while(rr-1 >= 0 && cc+1 < 8){
            rr--;
            cc++;
            if(Board.getDangerBoard()[rr][cc].getFill() == true){
                Board.getDangerBoard()[rr][cc].setDanger(this);
                if(rr-1 >= 0 && cc + 1 < 8  && Board.getDangerBoard()[rr][cc].getPiece() instanceof King)
                    Board.getDangerBoard()[rr-1][cc+1].setDanger(this);
                break;
            }else{
                Board.getDangerBoard()[rr][cc].setDanger(this);
            }
        }
        rr = r;
        cc = c;
        while(rr+1 <8 && cc+1 < 8){
            rr++;
            cc++;
            if(Board.getDangerBoard()[rr][cc].getFill() == true){
                Board.getDangerBoard()[rr][cc].setDanger(this);
                if(rr+1 < 8 && cc + 1 < 8 && Board.getDangerBoard()[rr][cc].getPiece() instanceof King)
                    Board.getDangerBoard()[rr+1][cc+1].setDanger(this);
                break;
            }else{
                Board.getDangerBoard()[rr][cc].setDanger(this);
            }
        }
        rr = r;
        cc = c;
        while(rr-1 >= 0 && cc-1 >= 0){
            rr--;
            cc--;
            if(Board.getDangerBoard()[rr][cc].getFill() == true){
                Board.getDangerBoard()[rr][cc].setDanger(this);
                if(rr-1 >= 0 && cc - 1 >= 0 && Board.getDangerBoard()[rr][cc].getPiece() instanceof King)
                    Board.getDangerBoard()[rr-1][cc-1].setDanger(this);
                break;
            }else{
                Board.getDangerBoard()[rr][cc].setDanger(this);
            }
        }
        rr = r;
        cc = c;
        while(rr+1 <8 && cc-1 >= 0){
            rr++;
            cc--;
            if(Board.getDangerBoard()[rr][cc].getFill() == true){
                Board.getDangerBoard()[rr][cc].setDanger(this);
                if(rr+1 < 8 && cc - 1 >= 0 && Board.getDangerBoard()[rr][cc].getPiece() instanceof King)
                    Board.getDangerBoard()[rr+1][cc-1].setDanger(this);
                break;
            }else{
                Board.getDangerBoard()[rr][cc].setDanger(this);
            }
        }

    }

   
    public void updateSpaces(){
        int rr = r;
        int cc = c;
        while(rr-1 >= 0 && cc+1 < 8){
            rr--;
            cc++;
            if(Board.getBoard()[rr][cc].getFill() == true){
                Board.getBoard()[rr][cc].setDanger(this);
                if(rr-1 >= 0 && cc + 1 < 8  && Board.getBoard()[rr][cc].getPiece() instanceof King)
                    Board.getBoard()[rr-1][cc+1].setDanger(this);
                break;
            }else{
                Board.getBoard()[rr][cc].setDanger(this);
            }
        }
        rr = r;
        cc = c;
        while(rr+1 <8 && cc+1 < 8){
            rr++;
            cc++;
            if(Board.getBoard()[rr][cc].getFill() == true){
                Board.getBoard()[rr][cc].setDanger(this);
                if(rr+1 < 8 && cc + 1 < 8 && Board.getBoard()[rr][cc].getPiece() instanceof King)
                    Board.getBoard()[rr+1][cc+1].setDanger(this);
                break;
            }else{
                Board.getBoard()[rr][cc].setDanger(this);
            }
        }
        rr = r;
        cc = c;
        while(rr-1 >= 0 && cc-1 >= 0){
            rr--;
            cc--;
            if(Board.getBoard()[rr][cc].getFill() == true){
                Board.getBoard()[rr][cc].setDanger(this);
                if(rr-1 >= 0 && cc - 1 >= 0 && Board.getBoard()[rr][cc].getPiece() instanceof King)
                    Board.getBoard()[rr-1][cc-1].setDanger(this);
                break;
            }else{
                Board.getBoard()[rr][cc].setDanger(this);
            }
        }
        rr = r;
        cc = c;
        while(rr+1 <8 && cc-1 >= 0){
            rr++;
            cc--;
            if(Board.getBoard()[rr][cc].getFill() == true){
                Board.getBoard()[rr][cc].setDanger(this);
                if(rr+1 < 8 && cc - 1 >= 0 && Board.getBoard()[rr][cc].getPiece() instanceof King)
                    Board.getBoard()[rr+1][cc-1].setDanger(this);
                break;
            }else{
                Board.getBoard()[rr][cc].setDanger(this);
            }
        }
    }
}