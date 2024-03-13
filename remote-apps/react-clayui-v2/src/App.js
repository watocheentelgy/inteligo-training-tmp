import "./styles.css";
//import "@clayui/css/lib/css/atlas.css";

import {
  ClayAlertTest1,
  ClayAlertTest2,
  ClayAlertTest3,
  ChartTest1,
  ColorPickerTest1,
} from "./Training";

export default function App() {
  return (
    <div className="App">
      <h1>Hello CodeSandbox</h1>
      <ClayAlertTest1 />
      <ClayAlertTest2 />
      <ClayAlertTest3 />
      <ChartTest1 />
      <ColorPickerTest1 />
    </div>
  );
}
