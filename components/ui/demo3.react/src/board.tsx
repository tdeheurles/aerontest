import * as React from "react";
import {Square} from "./square";

function calculateWinner(squares: string[]) {
    const lines = [
        [0, 1, 2],
        [3, 4, 5],
        [6, 7, 8],
        [0, 3, 6],
        [1, 4, 7],
        [2, 5, 8],
        [0, 4, 8],
        [2, 4, 6],
    ];
    for (let i = 0; i < lines.length; i++) {
        const [a, b, c] = lines[i];
        if (squares[a] && squares[a] === squares[b] && squares[a] === squares[c]) {
            return squares[a];
        }
    }
    return null;
}

export const Board =()=>{
    const initialState = Array(9).fill("");
    const [xIsNext, setXIsNext] = React.useState(true);
    const [squares, setSquares] = React.useState(initialState);
    const [state, setStatus] = React.useState('Next player: ' + (xIsNext ? 'X' : 'O'));
    const handleClick = React.useCallback(
        (i) => {
            if (calculateWinner(squares) || squares[i]) {
                return;
            }
            const newSquares = squares.slice();
            newSquares[i] = xIsNext ? 'X' : 'O';
            setSquares(newSquares);
            setXIsNext(!xIsNext);
        },
      [squares, xIsNext]
    );
    const renderSquare = React.useCallback(
        (i)=> <Square
            value={squares[i]}
            onClick={() => handleClick(i)}/>,
        [handleClick, squares]);

    React.useEffect(() => {
            const winner = calculateWinner(squares);
            if (winner) {
                setStatus('Winner: ' + winner);
            } else {
                setStatus('Next player: ' + (xIsNext ? 'X' : 'O'));
            }
        }, [squares, setStatus, xIsNext]);

    return <>
        <div className="status">{state}</div>
        <div className="board-row">
            {renderSquare(0)}
            {renderSquare(1)}
            {renderSquare(2)}
        </div>
        <div className="board-row">
            {renderSquare(3)}
            {renderSquare(4)}
            {renderSquare(5)}
        </div>
        <div className="board-row">
            {renderSquare(6)}
            {renderSquare(7)}
            {renderSquare(8)}
        </div>
        <button
            className="reset"
            onClick={()=>setSquares(initialState)}>
            reset
        </button>
    </>
}
