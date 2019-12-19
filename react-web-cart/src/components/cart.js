import React from 'react'

class Cart extends React.Component
{
    constructor(props)
    {
        super(props)

        
        this.state = {
            bicycles: this.props.bicycles,
            finalcost: 0
        }

        this.calculatecost()
    }

    calculatecost()
    {
        let final_cost = 0;

        for (let i = 0; i < this.props.bicycles.length; i++)
        {
            final_cost += this.props.bicycles[i].cost
        }

        this.state.finalcost = final_cost;
        this.setState(this.state)
    }

    showMessage()
    {
        this.state.bicycles.splice(0, this.state.bicycles.length)
        this.setState(this.state)

        return (
            <div>
            {alert('Покупка произведена!')}
            {this.render()}
            </div>
        );
    }

    deleteFromCart(index)
    {
        this.state.bicycles.splice(index, 1)
        this.setState(this.state)
        this.calculatecost()

        return (
            this.render()
        );
    }

    render()
    {
        if ( this.state.bicycles.length == 0 )
        {
            return (
                <div class = "Cart">
                    <p>Корзина пуста</p>
                </div>
            )
        }
        else
        {
            return (
                <div>
                    <h1> Корзина товаров </h1>
                    <table>
                        <tr>
                        <th> Name </th>
                        <th> Cost </th>
                        <th> Delete </th>
                        </tr>
                        {this.state.bicycles.map((bicycle, index) => (
                        <tr>
                            <td> {bicycle.name} </td>
                            <td> {bicycle.cost} </td>
                            <td>
                            <button onClick={() => { this.deleteFromCart(index) }}>
                                Убрать из корзины
                            </button>
                            </td>
                        </tr>
                            ))}
                            <tr>
                                <td > Итоговая сумма: {this.state.finalcost} </td>
                                <td colSpan="2"> 
                                <form action="/buy">
                                <button onClick={() => { this.showMessage() }}>
                                 Купить
                                </button>
                                </form>
                                </td>
                            </tr>
                    </table>
                </div>
            );
        }
    }
}

export default Cart