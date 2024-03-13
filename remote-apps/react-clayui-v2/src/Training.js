import React, { useState } from "react";
import ClayAlert from "@clayui/alert";
import ClayButton from "@clayui/button";
import ClayChart from "@clayui/charts";
import ClayColorPicker from "@clayui/color-picker";

//const spritemap = "/icons.svg";
const spritemap = "/o/classic-theme/images/clay/icons.svg";

const ClayAlertTest1 = () => {
  return (
    <>
      <div>
        <ClayAlert displayType="info" title="Info" spritemap={spritemap}>
          This is an info message.s
        </ClayAlert>
        <ClayAlert displayType="danger" spritemap={spritemap} title="Danger">
          This is a danger message.
        </ClayAlert>
        <ClayAlert
          displayType="info"
          title="With a Button"
          spritemap={spritemap}
        >
          {"This is an alert with a button!"}
          <ClayAlert.Footer>
            <ClayButton.Group>
              <ClayButton alert>{"View"}</ClayButton>
            </ClayButton.Group>
          </ClayAlert.Footer>
        </ClayAlert>
      </div>
    </>
  );
};

const ClayAlertTest2 = () => {
  return (
    <>
      <ClayAlert
        actions={
          <ClayButton.Group>
            <ClayButton alert>{"View"}</ClayButton>
          </ClayButton.Group>
        }
        spritemap={spritemap}
        displayType="info"
        title="With a Button"
        variant="inline"
      >
        {"This is an alert with a button!"}|
      </ClayAlert>
    </>
  );
};

const ClayAlertTest3 = () => {
  const [toastItems, setToastItems] = useState([]);
  return (
    <>
      <div>
        <button
          onClick={() => setToastItems([...toastItems, Math.random() * 100])}
        >
          {"Add Alert"}
        </button>
      </div>

      <ClayAlert.ToastContainer>
        {toastItems.map((value) => (
          <ClayAlert
            autoClose={5000}
            key={value}
            onClose={() => {
              setToastItems((prevItems) =>
                prevItems.filter((item) => item !== value)
              );
            }}
            spritemap={spritemap}
            title={"Hola:"}
          >{`My value is ${value}`}</ClayAlert>
        ))}
      </ClayAlert.ToastContainer>
    </>
  );
};

const ChartTest1 = () => {
  const [active, setActive] = useState(false);

  const COLUMNS = [
    ["data1", 100, 20, 30],
    ["data2", 20, 70, 100],
  ];
  const COLUMNS_2 = [
    ["data1", 10, 50, 60],
    ["data2", 70, 30, 10],
  ];

  return (
    <div>
      <button onClick={() => setActive((val) => !val)}>{"Change Data"}</button>

      <ClayChart
        data={{
          columns: active ? COLUMNS : COLUMNS_2,
          type: "bar",
        }}
      />
    </div>
  );
};

const ColorPickerTest1 = () => {
  const [customColors, setCustoms] = useState(["008000", "00FFFF", "0000FF"]);
  const [color, setColor] = useState(customColors[0]);

  return (
    <ClayColorPicker
      colors={customColors}
      label="Custom Colors"
      name="colorPicker2"
      onChange={setColor}
      onColorsChange={setCustoms}
      showHex={true}
      spritemap={spritemap}
      title="Custom Colors"
      value={color}
    />
  );
};

export {
  ClayAlertTest1,
  ClayAlertTest2,
  ClayAlertTest3,
  ChartTest1,
  ColorPickerTest1,
};
