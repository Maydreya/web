import React from 'react';
import './App.css';

import Cart from './components/cart'


function App() 
{
  const bicycles = [{name: 'Forward Flash 26', cost: 13930}, {name: 'Forward Next 29', cost: 25570}, {name: 'Forward Sporting 27,5', cost: 15090}, {name: 'Forward Apache 27,5', cost: 17370}]

  return (
    <div className="App">

      <Cart bicycles = {bicycles}/>

    </div>
  );
}

export default App;
