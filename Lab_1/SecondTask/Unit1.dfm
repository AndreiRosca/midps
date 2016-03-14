object TimerForm: TTimerForm
  Left = 381
  Top = 185
  Width = 429
  Height = 188
  Caption = 'MIDPS'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clMenuText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  PixelsPerInch = 96
  TextHeight = 13
  object taskLabel: TsLabel
    Left = 100
    Top = 8
    Width = 262
    Height = 16
    Caption = 'Implementing a C++ Builder stopwatch'
    ParentFont = False
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clRed
    Font.Height = -13
    Font.Name = 'MS Sans Serif'
    Font.Style = [fsBold]
  end
  object stopwatchLabel: TsLabel
    Left = 248
    Top = 32
    Width = 128
    Height = 13
    Caption = 'C++ Builder stopwatch'
    ParentFont = False
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clRed
    Font.Height = -12
    Font.Name = 'MS Sans Serif'
    Font.Style = [fsBold]
  end
  object timeEditor: TsEdit
    Left = 36
    Top = 48
    Width = 169
    Height = 21
    TabOrder = 0
    SkinData.SkinSection = 'EDIT'
    BoundLabel.Indent = 0
    BoundLabel.Font.Charset = DEFAULT_CHARSET
    BoundLabel.Font.Color = clWindowText
    BoundLabel.Font.Height = -11
    BoundLabel.Font.Name = 'MS Sans Serif'
    BoundLabel.Font.Style = []
    BoundLabel.Layout = sclLeft
    BoundLabel.MaxWidth = 0
    BoundLabel.UseSkinColor = True
  end
  object stopwatchEditor: TsEdit
    Left = 220
    Top = 48
    Width = 165
    Height = 21
    TabOrder = 1
    SkinData.SkinSection = 'EDIT'
    BoundLabel.Indent = 0
    BoundLabel.Font.Charset = DEFAULT_CHARSET
    BoundLabel.Font.Color = clWindowText
    BoundLabel.Font.Height = -11
    BoundLabel.Font.Name = 'MS Sans Serif'
    BoundLabel.Font.Style = []
    BoundLabel.Layout = sclLeft
    BoundLabel.MaxWidth = 0
    BoundLabel.UseSkinColor = True
  end
  object startButton: TsButton
    Left = 36
    Top = 104
    Width = 75
    Height = 25
    Caption = 'Start'
    TabOrder = 2
    OnClick = onStartButtonClick
    SkinData.SkinSection = 'BUTTON'
  end
  object stopButton: TsButton
    Left = 128
    Top = 104
    Width = 75
    Height = 25
    Caption = 'Stop'
    TabOrder = 3
    OnClick = onStopButtonClick
    SkinData.SkinSection = 'BUTTON'
  end
  object zeroButton: TsButton
    Left = 220
    Top = 104
    Width = 75
    Height = 25
    Caption = 'Zero'
    TabOrder = 4
    OnClick = onZeroButtonClick
    SkinData.SkinSection = 'BUTTON'
  end
  object exitButton: TsButton
    Left = 312
    Top = 104
    Width = 75
    Height = 25
    Caption = 'Exit'
    TabOrder = 5
    OnClick = onExitButtonClick
    SkinData.SkinSection = 'BUTTON'
  end
  object Timer1: TTimer
    Interval = 100
    OnTimer = onFirstTimerCall
    Left = 4
    Top = 4
  end
  object Timer2: TTimer
    OnTimer = onTimerTimerCall
    Left = 4
    Top = 40
  end
end
