package pieces;
import gui.Board;
import java.awt.Color;
public class King extends Piece
{  
    public King(int r, int c,int t){
        super(r,c,t);
        if(team == 1)
            loadImage(dir + "res/images/BlackKing.png");
        else
            loadImage(dir + "res/images/WhiteKing.png");
    }

    public void getMoves(){
        int rr = r;
        int cc = c;
        while(rr-1 >= 0 && cc+1 < 8){
            rr--;
            cc++;
            if(Board.getBoard()[rr][cc].getDanger() == false){
                if(Board.getBoard()[rr][cc].getFill() == true){
                    if(Board.getBoard()[rr][cc].getPiece().getTeam() != team){
                        Board.getBoard()[rr][cc].setBackground(Color.RED);
                    }
                    break;
                }else{
                    Board.getBoard()[rr][cc].setBackground(Color.RED);
                    break;
                }
            }else
                break;
        }
        rr = r;
        cc = c;
        while(rr+1 <8 && cc+1 < 8){
            rr++;
            cc++;
            if(Board.getBoard()[rr][cc].getDanger() == false)
                if(Board.getBoard()[rr][cc].getFill() == true){
                    if(Board.getBoard()[rr][cc].getPiece().getTeam() != team){
                        Board.getBoard()[rr][cc].setBackground(Color.RED);
                    }
                    break;
                }else{
                    Board.getBoard()[rr][cc].setBackground(Color.RED);
                    break;
                }
            else
                break;
        }
        rr = r;
        cc = c;
        while(rr-1 >= 0 && cc-1 >= 0){
            rr--;
            cc--;
            if(Board.getBoard()[rr][cc].getDanger() == false)
                if(Board.getBoard()[rr][cc].getFill() == true){
                    if(Board.getBoard()[rr][cc].getPiece().getTeam() != team){
                        Board.getBoard()[rr][cc].setBackground(Color.RED);
                    }
                    break;
                }else{
                    Board.getBoard()[rr][cc].setBackground(Color.RED);
                    break;
                }
            else
                break;
        }
        rr = r;
        cc = c;
        while(rr+1 <8 && cc-1 >= 0){
            rr++;
            cc--;
            if(Board.getBoard()[rr][cc].getDanger() == false)
                if(Board.getBoard()[rr][cc].getFill() == true){
                    if(Board.getBoard()[rr][cc].getPiece().getTeam() != team){
                        Board.getBoard()[rr][cc].setBackground(Color.RED);
                    }
                    break;
                }else{
                    Board.getBoard()[rr][cc].setBackground(Color.RED);
                    break;
                }
            else
                break;
        }
        for(int rrr = r-1;rrr>=0;rrr--){
            if(Board.getBoard()[rrr][c].getDanger() == false)
                if(Board.getBoard()[rrr][c].getFill() == true){
                    if(Board.getBoard()[rrr][c].getPiece().getTeam() != team){
                        Board.getBoard()[rrr][c].setBackground(Color.RED);
                    }
                    break;
                }else{
                    Board.getBoard()[rrr][c].setBackground(Color.RED);
                    break;
                }
            else
                break;
        }    
        for(int rrr = r+1;rrr<8;rrr++){
            if(Board.getBoard()[rrr][c].getDanger() == false)
                if(Board.getBoard()[rrr][c].getFill() == true){
                    if(Board.getBoard()[rrr][c].getPiece().getTeam() != team){
                        Board.getBoard()[rrr][c].setBackground(Color.RED);
                    }
                    break;
                }else{
                    Board.getBoard()[rrr][c].setBackground(Color.RED);
                    break;
                }
            else
                break;
        }
        for(int ccc = c-1;ccc>=0;ccc--){
            if(Board.getBoard()[r][ccc].getDanger() == false)
                if(Board.getBoard()[r][ccc].getFill() == true){
                    if(Board.getBoard()[r][ccc].getPiece().getTeam() != team){
                        Board.getBoard()[r][ccc].setBackground(Color.RED);
                    }
                    break;
                }else{
                    Board.getBoard()[r][ccc].setBackground(Color.RED);

                    break;
                }
            else
                break;
        }
        for(int ccc = c+1;ccc<8;ccc++){
            if(Board.getBoard()[r][ccc].getDanger() == false)
                if(Board.getBoard()[r][ccc].getFill() == true){
                    if(Board.getBoard()[r][ccc].getPiece().getTeam() != team){
                        Board.getBoard()[r][ccc].setBackground(Color.RED);
                    }
                    break;
                }else{
                    Board.getBoard()[r][ccc].setBackground(Color.RED);
                    break;
                }
            else
                break;
        }
        if(hasMoved == false && Board.getBoard()[r][c+3].getFill() == true && Board.getBoard()[r][c+3].getPiece() instanceof Rook && Board.getBoard()[r][c+3].getPiece().getMove() == false
        && Board.getBoard()[r][c+1].getFill() == false && Board.getBoard()[r][c+2].getFill() == false){
            if(team == 0 && Board.getBoard()[r][c].dangerBlack() == false && Board.getBoard()[r][c+1].dangerBlack() == false  && Board.getBoard()[r][c+2].dangerBlack() == false)
                Board.getBoard()[r][c+2].setBackground(Color.BLUE);
            else if(team == 1 && Board.getBoard()[r][c].dangerWhite() == false && Board.getBoard()[r][c+1].dangerWhite() == false  && Board.getBoard()[r][c+2].dangerWhite() == false)
                Board.getBoard()[r][c+2].setBackground(Color.BLUE);
        }
        if(hasMoved == false && Board.getBoard()[r][c-4].getFill() == true && Board.getBoard()[r][c-4].getPiece() instanceof Rook && Board.getBoard()[r][c-4].getPiece().getMove() == false
        && Board.getBoard()[r][c-3].getFill() == false && Board.getBoard()[r][c-2].getFill() == false && Board.getBoard()[r][c-1].getFill() == false){
            if(team == 0 && Board.getBoard()[r][c].dangerBlack() == false && Board.getBoard()[r][c-3].dangerBlack() == false  && Board.getBoard()[r][c-2].dangerBlack() == false&& Board.getBoard()[r][c-1].dangerBlack() == false)
                Board.getBoard()[r][c-2].setBackground(Color.BLUE);
            else if(team == 1 && Board.getBoard()[r][c].dangerWhite() == false && Board.getBoard()[r][c-3].dangerWhite() == false  && Board.getBoard()[r][c-2].dangerWhite() == false && Board.getBoard()[r][c-1].dangerWhite() == false)
                Board.getBoard()[r][c-2].setBackground(Color.BLUE);
        }

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
                break;
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
                break;
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
                break;
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
                break;
            }
        }
        for(int rrr = r-1;rrr>=0;rrr--){
            if(Board.getDangerBoard()[rrr][c].getFill() == true){
                Board.getDangerBoard()[rrr][c].setDanger(this);
                if(rrr-1 >= 0 && Board.getDangerBoard()[rrr][c].getPiece() instanceof King)
                    Board.getDangerBoard()[rrr-1][c].setDanger(this);
                break;
            }else{
                Board.getDangerBoard()[rrr][c].setDanger(this);
                break;
            }
        }
        for(int rrr = r+1;rrr<8;rrr++){
            if(Board.getDangerBoard()[rrr][c].getFill() == true){
                Board.getDangerBoard()[rrr][c].setDanger(this);
                if(rrr+1 < 8 && Board.getDangerBoard()[rrr][c].getPiece() instanceof King)
                    Board.getDangerBoard()[rrr+1][c].setDanger(this);
                break;
            }else{
                Board.getDangerBoard()[rrr][c].setDanger(this);
                break;
            }
        }
        for(int ccc = c-1;ccc>=0;ccc--){
            if(Board.getDangerBoard()[r][ccc].getFill() == true){
                Board.getDangerBoard()[r][ccc].setDanger(this);
                if(ccc-1 >= 0 && Board.getDangerBoard()[r][ccc].getPiece() instanceof King)
                    Board.getDangerBoard()[r][ccc-1].setDanger(this);
                break;
            }else{
                Board.getDangerBoard()[r][ccc].setDanger(this);
                break;
            }
        }
        for(int ccc = c+1;ccc<8;ccc++){
            if(Board.getDangerBoard()[r][ccc].getFill() == true){
                Board.getDangerBoard()[r][ccc].setDanger(this);
                if(ccc+1 < 8 && Board.getDangerBoard()[r][ccc].getPiece() instanceof King)
                    Board.getDangerBoard()[r][ccc+1].setDanger(this);
                break;
            }else{
                Board.getDangerBoard()[r][ccc].setDanger(this);
                break;
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
                break;
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
                break;
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
                break;
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
                break;
            }
        }
        for(int rrr = r-1;rrr>=0;rrr--){
            if(Board.getBoard()[rrr][c].getFill() == true){
                Board.getBoard()[rrr][c].setDanger(this);
                if(rrr-1 >= 0 && Board.getBoard()[rrr][c].getPiece() instanceof King)
                    Board.getBoard()[rrr-1][c].setDanger(this);
                break;
            }else{
                Board.getBoard()[rrr][c].setDanger(this);
                break;
            }
        }
        for(int rrr = r+1;rrr<8;rrr++){
            if(Board.getBoard()[rrr][c].getFill() == true){
                Board.getBoard()[rrr][c].setDanger(this);
                if(rrr+1 < 8 && Board.getBoard()[rrr][c].getPiece() instanceof King)
                    Board.getBoard()[rrr+1][c].setDanger(this);
                break;
            }else{
                Board.getBoard()[rrr][c].setDanger(this);
                break;
            }
        }
        for(int ccc = c-1;ccc>=0;ccc--){
            if(Board.getBoard()[r][ccc].getFill() == true){
                Board.getBoard()[r][ccc].setDanger(this);
                if(ccc-1 >= 0 && Board.getBoard()[r][ccc].getPiece() instanceof King)
                    Board.getBoard()[r][ccc-1].setDanger(this);
                break;
            }else{
                Board.getBoard()[r][ccc].setDanger(this);
                break;
            }
        }
        for(int ccc = c+1;ccc<8;ccc++){
            if(Board.getBoard()[r][ccc].getFill() == true){
                Board.getBoard()[r][ccc].setDanger(this);
                if(ccc+1 < 8 && Board.getBoard()[r][ccc].getPiece() instanceof King)
                    Board.getBoard()[r][ccc+1].setDanger(this);
                break;
            }else{
                Board.getBoard()[r][ccc].setDanger(this);
                break;
            }
        }
    } 

    public boolean hasValidMove(){
        int rr = r;
        int cc = c;
        while(rr-1 >= 0 && cc+1 < 8){
            rr--;
            cc++;
            if(isValidMove(rr,cc)){
                if(Board.getBoard()[rr][cc].getFill() == true){
                    if(Board.getBoard()[rr][cc].getPiece().getTeam() != team){
                        return true;
                    }
                    break;
                }else{
                    return true;
                }
            }else
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
            else
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
            else
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
            else
                break;
        }
        for(int rrr = r-1;rrr>=0;rrr--){
            if(isValidMove(rrr,c))
                if(Board.getBoard()[rrr][c].getFill() == true){
                    if(Board.getBoard()[rrr][c].getPiece().getTeam() != team){
                        return true;
                    }
                    break;
                }else{
                    return true;
                }
            else
                break;
        }    
        for(int rrr = r+1;rrr<8;rrr++){
            if(isValidMove(rrr,c))
                if(Board.getBoard()[rrr][c].getFill() == true){
                    if(Board.getBoard()[rrr][c].getPiece().getTeam() != team){
                        return true;
                    }
                    break;
                }else{
                    return true;
                }
            else
                break;
        }
        for(int ccc = c-1;ccc>=0;ccc--){
            if(isValidMove(r,ccc))
                if(Board.getBoard()[r][ccc].getFill() == true){
                    if(Board.getBoard()[r][ccc].getPiece().getTeam() != team){
                        return true;
                    }
                    break;
                }else{
                    return true;
                }
            else
                break;
        }
        for(int ccc = c+1;ccc<8;ccc++){
            if(isValidMove(r,ccc))
                if(Board.getBoard()[r][ccc].getFill() == true){
                    if(Board.getBoard()[r][ccc].getPiece().getTeam() != team){
                        return true;
                    }
                    break;
                }else{
                    return true;
                }
            else
                break;
        }
        return false;
    }
}

