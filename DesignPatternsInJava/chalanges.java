
public JSlider slider() {
    if (slider == null) {
    slider = new JSlider();
    sliderMax = slider.getMaximum();
    sliderMin = slider.getMinimum();
    slider.addChangeListener( ?? );
    slider.setValue(slider.getMinimum());
    }
    return slider;
    }

public void stateChanged(ChangeEvent e) {
    double val = slider.getValue();
    double tp = (val - sliderMin) / (sliderMax - sliderMin);
    burnPanel(). ?? ( ?? );
    thrustPanel(). ?? ( ?? );
    valueLabel(). ?? ( ?? );
    }