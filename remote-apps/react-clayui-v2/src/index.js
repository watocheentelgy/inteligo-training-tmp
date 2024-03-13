import React from 'react';
import ReactDOM from 'react-dom';

/*
import { StrictMode } from "react";
import { createRoot } from "react-dom/client";*/

import App from "./App";
/*
const rootElement = document.getElementById("root");
const root = createRoot(rootElement);

root.render(
  <StrictMode>
    <App />
  </StrictMode>
);*/

class WebComponent extends HTMLElement {
  connectedCallback() {
    ReactDOM.render(
      <App />,
      this
    );
  }
}

const ELEMENT_ID = 'clayui-react-app-v1';

if(!customElements.get(ELEMENT_ID)){
  customElements.define(ELEMENT_ID, WebComponent);
}

