package org.ankur.advent2018;

import org.ankur.advent.util.FileReader;
import org.ankur.advent2018.domain.Cart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.ankur.advent.util.LogWriter.logSameLine;
import static org.ankur.advent.util.LogWriter.newLine;

public class D13MineCartMadness {
    private char[][] mine;

    public String crashPoint(String fileName) {
        List<Cart> carts = init(FileReader.readFile(fileName));
        do {
            for (Cart cart : carts) {
                move(cart);
                for (Cart cart1 : carts) {
                    if (cart1 != cart && cart1.equals(cart)) {
                        mine[cart.getY()][cart.getX()] = 'X';
                        displayMine(carts);
                        return cart.getX() + "," + cart.getY();
                    }
                }
            }
            Collections.sort(carts);
        } while (true);
    }

    public String lastCar(String fileName) {
        List<Cart> carts = init(FileReader.readFile(fileName));
        do {
            for (Cart cart : carts) {
                move(cart);
                for (Cart cart1 : carts) {
                    if (cart1 != cart && cart1.compareTo(cart) == 0) {
                        cart.setCrashed(true);
                        cart1.setCrashed(true);
                    }
                }
            }
            removeCrashedCarts(carts);
        } while (carts.size() > 1);

        return carts.get(0).getX() + "," + carts.get(0).getY();
    }

    private void removeCrashedCarts(List<Cart> carts) {
        carts.removeIf(Cart::isCrashed);
        Collections.sort(carts);
    }

    private void move(Cart cart) {
        int x = cart.getX();
        int y = cart.getY();
        switch (cart.getDirection()) {
            case UP:
                y--;
                break;
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
        }

        updateDirection(cart, x, y);
    }

    private void updateDirection(Cart cart, int x, int y) {
        cart.setX(x);
        cart.setY(y);
        switch (mine[y][x]) {
            case '-':
            case '|':
                return;
            case '\\':
                switch (cart.getDirection()) {
                    case RIGHT:
                        cart.setDirection(Cart.Direction.DOWN);
                        break;
                    case DOWN:
                        cart.setDirection(Cart.Direction.RIGHT);
                        break;
                    case LEFT:
                        cart.setDirection(Cart.Direction.UP);
                        break;
                    case UP:
                        cart.setDirection(Cart.Direction.LEFT);
                        break;
                }
                break;
            case '/':
                switch (cart.getDirection()) {
                    case RIGHT:
                        cart.setDirection(Cart.Direction.UP);
                        break;
                    case UP:
                        cart.setDirection(Cart.Direction.RIGHT);
                        break;
                    case LEFT:
                        cart.setDirection(Cart.Direction.DOWN);
                        break;
                    case DOWN:
                        cart.setDirection(Cart.Direction.LEFT);
                        break;

                }
                break;
            case '+':
                switch (cart.getDirection()) {
                    case DOWN:
                        switch (cart.getNext()) {
                            case LEFT:
                                cart.setDirection(Cart.Direction.RIGHT);
                                break;
                            case RIGHT:
                                cart.setDirection(Cart.Direction.LEFT);
                                break;
                            case STRAIGHT:
                                cart.setDirection(Cart.Direction.DOWN);
                                break;
                        }
                        break;
                    case LEFT:
                        switch (cart.getNext()) {
                            case LEFT:
                                cart.setDirection(Cart.Direction.DOWN);
                                break;
                            case RIGHT:
                                cart.setDirection(Cart.Direction.UP);
                                break;
                            case STRAIGHT:
                                cart.setDirection(Cart.Direction.LEFT);
                                break;
                        }
                        break;
                    case UP:
                        switch (cart.getNext()) {
                            case LEFT:
                                cart.setDirection(Cart.Direction.LEFT);
                                break;
                            case RIGHT:
                                cart.setDirection(Cart.Direction.RIGHT);
                                break;
                            case STRAIGHT:
                                cart.setDirection(Cart.Direction.UP);
                                break;
                        }
                        break;
                    case RIGHT:
                        switch (cart.getNext()) {
                            case LEFT:
                                cart.setDirection(Cart.Direction.UP);
                                break;
                            case RIGHT:
                                cart.setDirection(Cart.Direction.DOWN);
                                break;
                            case STRAIGHT:
                                cart.setDirection(Cart.Direction.RIGHT);
                                break;
                        }
                        break;
                }

                switch (cart.getNext()) {
                    case LEFT:
                        cart.setNext(Cart.Turn.STRAIGHT);
                        break;
                    case STRAIGHT:
                        cart.setNext(Cart.Turn.RIGHT);
                        break;
                    case RIGHT:
                        cart.setNext(Cart.Turn.LEFT);
                        break;

                }
                break;

            default:
                break;
        }
    }

    private void displayMine(List<Cart> carts) {
        if (carts.isEmpty() || mine.length > 20) {
            return;
        }

        Iterator<Cart> iterator = carts.iterator();
        Cart current = iterator.next();
        for (int i = 0; i < mine.length; i++) {
            for (int j = 0; j < mine[0].length; j++) {
                if (mine[i][j] == 'X') {
                    logSameLine(mine[i][j] + "");
                } else if (current.getX() == j && current.getY() == i) {
                    logSameLine(current.getDirection() + "");
                    if (iterator.hasNext()) {
                        current = iterator.next();
                    }
                } else {
                    logSameLine(mine[i][j] + "");
                }
            }
            newLine();
        }
        newLine();
    }

    private List<Cart> init(List<String> cartMap) {
        mine = new char[cartMap.size()][cartMap.get(0).length()];
        List<Cart> carts = new ArrayList<>();
        int index = 0;
        for (String str : cartMap) {
            char[] chars = str.toCharArray();
            mine[index ++] = chars;
        }

        for (int i = 0; i < mine.length; i++) {
            for (int j = 0; j < mine[0].length; j++) {
                if (mine.length < 20) {
                    logSameLine(mine[i][j] + "");
                }
                Cart.Direction direction = null;
                switch (mine[i][j]) {
                    case '>':
                        direction = Cart.Direction.RIGHT;
                        mine[i][j] = '-';
                        break;
                    case '<':
                        direction = Cart.Direction.LEFT;
                        mine[i][j] = '-';
                        break;
                    case '^':
                        direction = Cart.Direction.UP;
                        mine[i][j] = '|';
                        break;
                    case 'v':
                        direction = Cart.Direction.DOWN;
                        mine[i][j] = '|';
                        break;
                    default:
                        break;
                }
                if (null != direction) {
                    Cart cart = Cart.builder()
                            .x(j)
                            .y(i)
                            .direction(direction)
                            .next(Cart.Turn.LEFT)
                            .build();
                    carts.add(cart);
                }
            }
            if (mine.length < 20) {
                newLine();
            }
        }

        Collections.sort(carts);

        return carts;
    }
}
