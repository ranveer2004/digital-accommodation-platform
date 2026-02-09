import Colors from "../utils/Colors";

function DividerWithText({ text }) {
  const lineThickness = '0.5px'

  return (
    <div style={{
      display: 'flex',
      alignItems: 'center',
      width: '80%',
    }}>
      <div style={{ flex: 1, height: lineThickness, backgroundColor: Colors.grey }} />
      <span 
        style={{ 
          color: Colors.primary,
          margin: '10px 10px', whiteSpace: 'nowrap' 
        }}
      >
        {text}
      </span>
      <div style={{ flex: 1, height: lineThickness, backgroundColor: Colors.grey }} />
    </div>
  );
}

export default DividerWithText;